package com.pellto.youtoy.application.usecase;

import com.pellto.youtoy.domain.channel.service.ChannelReadService;
import com.pellto.youtoy.domain.comment.dto.CommentDto;
import com.pellto.youtoy.domain.comment.dto.CreateMentionCommand;
import com.pellto.youtoy.domain.comment.dto.UpdateCommentCommand;
import com.pellto.youtoy.domain.comment.service.CommentReadService;
import com.pellto.youtoy.domain.comment.service.CommentWriteService;
import com.pellto.youtoy.domain.comment.service.MentionWriteService;
import com.pellto.youtoy.util.ChannelHandlePattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class UpdateCommentUsecase {
    private final ChannelReadService channelReadService;
    private final CommentWriteService commentWriteService;
    private final CommentReadService commentReadService;
    private final MentionWriteService mentionWriteService;

    public CommentDto execute(UpdateCommentCommand cmd) {
        var commentDto = commentReadService.get(cmd.id());
        if (ChannelHandlePattern.hasPattern(commentDto.content())) {
            mentionWriteService.deleteByCommentId(cmd.id());
        }
        if (!ChannelHandlePattern.hasPattern(cmd.content())) {
            return commentWriteService.update(cmd);
        }
        var mentionedChannelHandles = ChannelHandlePattern.extractChannelHandle(cmd.content());
        var updatedComment = commentWriteService.update(cmd);
        mentionedChannelHandles.forEach((mentionedChannelHandle) -> {
            var channelDto = channelReadService.getByHandle(mentionedChannelHandle);
            var createMentionCommand = new CreateMentionCommand(
                    commentDto.id(), channelDto.id(), commentDto.createdAt()
            );
            mentionWriteService.create(createMentionCommand);
        });
        return updatedComment;
    }
}
