package com.pellto.youtoy.application.usecase;

import com.pellto.youtoy.domain.comment.dto.CreateCommentCommand;
import com.pellto.youtoy.domain.comment.entity.Comment;
import com.pellto.youtoy.domain.comment.service.CommentWriteService;
import com.pellto.youtoy.domain.video.service.ShortReadService;
import com.pellto.youtoy.domain.video.service.VideoReadService;
import com.pellto.youtoy.util.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateCommentUsecase {
    private final VideoReadService videoReadService;
    private final ShortReadService shortReadService;
    private final CommentWriteService commentWriteService;

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

        return commentWriteService.create(cmd);
    }
}
