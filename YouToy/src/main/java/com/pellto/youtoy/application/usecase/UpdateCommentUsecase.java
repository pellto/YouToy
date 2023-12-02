package com.pellto.youtoy.application.usecase;

import com.pellto.youtoy.domain.channel.service.ChannelReadService;
import com.pellto.youtoy.domain.comment.dto.CommentDto;
import com.pellto.youtoy.domain.comment.dto.CreateMentionCommand;
import com.pellto.youtoy.domain.comment.dto.UpdateCommentCommand;
import com.pellto.youtoy.domain.comment.service.CommentWriteService;
import com.pellto.youtoy.domain.comment.service.MentionWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class UpdateCommentUsecase {
    private final ChannelReadService channelReadService;
    private final CommentWriteService commentWriteService;
    private final MentionWriteService mentionWriteService;

    public CommentDto execute(UpdateCommentCommand cmd) {
        if (cmd.mentionedChannelHandle() == null) {
            return commentWriteService.update(cmd);
        }
        var channel = channelReadService.getByHandle(cmd.mentionedChannelHandle());
        var commentDto = commentWriteService.update(cmd);
        // TODO: add comment updatedAt column
        var createMentionCommand = new CreateMentionCommand(
                commentDto.getId(),
                channel.id(),
                commentDto.getCreatedAt()
        );
        mentionWriteService.create(createMentionCommand);
        return commentDto;
    }
}
