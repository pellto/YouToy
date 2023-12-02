package com.pellto.youtoy.application.usecase;

import com.pellto.youtoy.domain.channel.service.ChannelReadService;
import com.pellto.youtoy.domain.comment.dto.CreateCommentCommand;
import com.pellto.youtoy.domain.comment.dto.CreateMentionCommand;
import com.pellto.youtoy.domain.comment.entity.Comment;
import com.pellto.youtoy.domain.comment.service.CommentWriteService;
import com.pellto.youtoy.domain.comment.service.MentionWriteService;
import com.pellto.youtoy.domain.user.service.UserReadService;
import com.pellto.youtoy.domain.video.service.ShortReadService;
import com.pellto.youtoy.domain.video.service.VideoReadService;
import com.pellto.youtoy.util.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class CreateCommentUsecase {
    private final ChannelReadService channelReadService;
    private final VideoReadService videoReadService;
    private final ShortReadService shortReadService;
    private final CommentWriteService commentWriteService;
    private final MentionWriteService mentionWriteService;

    public Comment execute(CreateCommentCommand cmd) {
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
        if (cmd.mentionedChannelHandle() == null) {
            return commentWriteService.create(cmd);
        }
        var channel = channelReadService.getByHandle(cmd.mentionedChannelHandle());
        var comment = commentWriteService.create(cmd);
        var createMentionCommand = new CreateMentionCommand(comment.getId(), channel.id(), comment.getCreatedAt());
        mentionWriteService.create(createMentionCommand);
        return comment;
    }
}
