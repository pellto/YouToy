package com.pellto.youtoy.application.usecase;

import com.pellto.youtoy.domain.comment.entity.Comment;
import com.pellto.youtoy.domain.comment.service.CommentReadService;
import com.pellto.youtoy.domain.video.service.ShortReadService;
import com.pellto.youtoy.domain.video.service.VideoReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetVideoCommentsUsecase {
    private final VideoReadService videoReadService;
    private final ShortReadService shortReadService;
    private final CommentReadService commentReadService;

    // TODO: Comment to dto
    public List<Comment> execute(Long videoId, Boolean isVideo) {
        if (isVideo) {
            if (!videoReadService.existVideo(videoId)) {
                // TODO: Convert to Custom Error
                throw new UnsupportedOperationException("존재하지 않습니다.");
            }
        } else {
            if (!shortReadService.existShort(videoId)) {
                // TODO: Convert to Custom Error
                throw new UnsupportedOperationException("존재하지 않습니다.");
            }
        }

        return commentReadService.getByVideoId(videoId, isVideo);
    }
}
