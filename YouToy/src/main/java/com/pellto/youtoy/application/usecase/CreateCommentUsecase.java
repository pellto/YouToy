package com.pellto.youtoy.application.usecase;

import com.pellto.youtoy.domain.channel.service.ChannelReadService;
import com.pellto.youtoy.domain.comment.dto.CommentDto;
import com.pellto.youtoy.domain.comment.dto.CreateCommentCommand;
import com.pellto.youtoy.domain.comment.dto.CreateMentionCommand;
import com.pellto.youtoy.domain.comment.service.CommentWriteService;
import com.pellto.youtoy.domain.comment.service.MentionWriteService;
import com.pellto.youtoy.domain.view.service.ShortReadService;
import com.pellto.youtoy.domain.view.service.VideoReadService;
import com.pellto.youtoy.util.ChannelHandlePattern;
import com.pellto.youtoy.util.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
@Transactional
public class CreateCommentUsecase {
    private final ChannelReadService channelReadService;
    private final VideoReadService videoReadService;
    private final ShortReadService shortReadService;
    private final CommentWriteService commentWriteService;
    private final MentionWriteService mentionWriteService;

    public CommentDto execute(CreateCommentCommand cmd) {
        if (cmd.video()) {
            if (!videoReadService.existVideo(cmd.videoId())) {
                // TODO: Convert to Custom Error
                throw new UnsupportedOperationException(ErrorCode.NOT_EXIST_VIDEO.getMessage());
            }
        } else {
            if (!shortReadService.existShort(cmd.videoId())) {
                // TODO: Convert to Custom Error
                throw new UnsupportedOperationException(ErrorCode.NOT_EXIST_SHORT.getMessage());
            }
        }
        if (!ChannelHandlePattern.hasPattern(cmd.content())) {
            return commentWriteService.create(cmd);
        }
        var mentionedChannelHandles = ChannelHandlePattern.extractChannelHandle(cmd.content());
        var comment = commentWriteService.create(cmd);

        mentionedChannelHandles.forEach((mentionedChannelHandle) -> saveMention(
                mentionedChannelHandle,
                comment.id(),
                comment.createdAt()
        ));
        return comment;
    }

    private void saveMention(String mentionedChannelHandle, Long commentId, LocalDateTime commentCreatedAt) {
        var channel = channelReadService.getByHandle(mentionedChannelHandle);
        var createMentionCommand = new CreateMentionCommand(commentId, channel.id(), commentCreatedAt);
        mentionWriteService.create(createMentionCommand);
    }
}
