package com.pellto.youtoy.application.usecase;

import com.pellto.youtoy.domain.comment.service.CommentWriteService;
import com.pellto.youtoy.domain.like.dto.LikeDto;
import com.pellto.youtoy.domain.like.service.DislikeWriteService;
import com.pellto.youtoy.domain.like.service.LikeReadService;
import com.pellto.youtoy.domain.like.service.LikeWriteService;
import com.pellto.youtoy.domain.video.service.ShortWriteService;
import com.pellto.youtoy.domain.video.service.VideoWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.pellto.youtoy.util.types.VideoTypes.VIDEO_TYPE;

@RequiredArgsConstructor
@Service
@Transactional
public class CancelLikeUsecase {
    private final LikeWriteService likeWriteService;
    private final DislikeWriteService dislikeWriteService;
    private final LikeReadService likeReadService;
    private final CommentWriteService commentWriteService;
    private final VideoWriteService videoWriteService;
    private final ShortWriteService shortWriteService;

    public void executeLike(Long id) {
        LikeDto like = likeReadService.getById(id);
        likeWriteService.cancel(like);
        if (like.videoType() == null) {
            // is comment
            commentWriteService.decreaseLikeCount(like.commentId());
        } else if (like.videoType().equals(VIDEO_TYPE.getValue())) {
            // is video
            videoWriteService.decreaseLikeCount(like.videoId());
        } else {
            // is shorts
            shortWriteService.decreaseLikeCount(like.videoId());
        }
    }

    public void executeDislike(Long id) {
        dislikeWriteService.cancel(id);
    }
}
