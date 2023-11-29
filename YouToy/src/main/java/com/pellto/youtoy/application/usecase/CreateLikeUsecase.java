package com.pellto.youtoy.application.usecase;

import com.pellto.youtoy.domain.comment.service.CommentReadService;
import com.pellto.youtoy.domain.like.dto.CreateLikeCommand;
import com.pellto.youtoy.domain.like.service.DislikeReadService;
import com.pellto.youtoy.domain.like.service.DislikeWriteService;
import com.pellto.youtoy.domain.like.service.LikeReadService;
import com.pellto.youtoy.domain.like.service.LikeWriteService;
import com.pellto.youtoy.domain.video.service.ShortReadService;
import com.pellto.youtoy.domain.video.service.VideoReadService;
import com.pellto.youtoy.util.error.ErrorCode;
import com.pellto.youtoy.util.types.VideoTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CreateLikeUsecase {
    private final LikeWriteService likeWriteService;
    private final LikeReadService likeReadService;
    private final DislikeWriteService dislikeWriteService;
    private final DislikeReadService dislikeReadService;
    private final VideoReadService videoReadService;
    private final ShortReadService shortReadService;
    private final CommentReadService commentReadService;

    public void executeLike(CreateLikeCommand cmd) {
        check(cmd);
        crossCheck(cmd, true);
        likeWriteService.like(cmd);
    }

    public void executeDislike(CreateLikeCommand cmd) {
        check(cmd);
        crossCheck(cmd, false);
        dislikeWriteService.dislike(cmd);
    }

    private void crossCheck(CreateLikeCommand cmd, boolean isLike) {
        if (isLike) {
            // dislike 체크
            var dislike = dislikeReadService.getByCreateCmd(cmd);
            if (dislike != null) dislikeWriteService.cancel(dislike.id());
        } else {
            // like 체크
            var like = likeReadService.getByCreateCmd(cmd);
            if (like != null) likeWriteService.cancel(like.id());
        }
    }

    private void check(CreateLikeCommand cmd) {
        if (cmd.videoType() == null) {
            // comment's like
            if (cmd.commentId() == null) {
                throw new UnsupportedOperationException(ErrorCode.UNSUPPORTED_LIKE_CASE.getMessage());
            }
            if (!commentReadService.existComment(cmd.commentId())) {
                throw new UnsupportedOperationException(ErrorCode.NOT_EXIST_COMMENT.getMessage());
            }
        } else if (Objects.equals(cmd.videoType(), VideoTypes.VIDEO_TYPE.getValue())) {
            // video's like
            if (!videoReadService.existVideo(cmd.videoId())) {
                throw new UnsupportedOperationException(ErrorCode.NOT_EXIST_VIDEO.getMessage());
            }
        } else {
            // shorts' like
            if (!shortReadService.existShort(cmd.videoId())) {
                throw new UnsupportedOperationException(ErrorCode.NOT_EXIST_SHORT.getMessage());
            }
        }
    }
}
