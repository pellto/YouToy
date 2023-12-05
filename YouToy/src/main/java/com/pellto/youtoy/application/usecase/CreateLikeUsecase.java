package com.pellto.youtoy.application.usecase;

import com.pellto.youtoy.domain.comment.service.CommentReadService;
import com.pellto.youtoy.domain.comment.service.CommentWriteService;
import com.pellto.youtoy.domain.like.dto.CreateLikeCommand;
import com.pellto.youtoy.domain.like.service.DislikeReadService;
import com.pellto.youtoy.domain.like.service.DislikeWriteService;
import com.pellto.youtoy.domain.like.service.LikeReadService;
import com.pellto.youtoy.domain.like.service.LikeWriteService;
import com.pellto.youtoy.domain.view.service.ShortReadService;
import com.pellto.youtoy.domain.view.service.ShortWriteService;
import com.pellto.youtoy.domain.view.service.VideoReadService;
import com.pellto.youtoy.domain.view.service.VideoWriteService;
import com.pellto.youtoy.util.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static com.pellto.youtoy.util.types.VideoTypes.*;

@RequiredArgsConstructor
@Service
@Transactional
public class CreateLikeUsecase {
    private final LikeWriteService likeWriteService;
    private final LikeReadService likeReadService;
    private final DislikeWriteService dislikeWriteService;
    private final DislikeReadService dislikeReadService;
    private final VideoReadService videoReadService;
    private final VideoWriteService videoWriteService;
    private final ShortWriteService shortWriteService;
    private final ShortReadService shortReadService;
    private final CommentReadService commentReadService;
    private final CommentWriteService commentWriteService;

    public void executeLike(CreateLikeCommand cmd) {
        check(cmd);
        Boolean isIncrease = crossCheck(cmd, true);
        likeWriteService.like(cmd);
        fluctuateLikeCount(cmd, isIncrease);
    }

    public void executeDislike(CreateLikeCommand cmd) {
        check(cmd);
        Boolean isIncrease = crossCheck(cmd, false);
        dislikeWriteService.dislike(cmd);
        fluctuateLikeCount(cmd, isIncrease);
    }

    private Boolean crossCheck(CreateLikeCommand cmd, boolean isLike) {
        if (isLike) {
            // dislike 체크
            var dislike = dislikeReadService.getByCreateCmd(cmd);
            if (dislike != null) dislikeWriteService.cancel(dislike.id());
        } else {
            // like 체크
            var like = likeReadService.getByCreateCmd(cmd);
            if (like != null) {
                likeWriteService.cancel(like.id());
                return false;
            }
            return null;
        }
        return true;
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
        } else if (cmd.videoType().equals(VIDEO_TYPE.getValue())) {
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

    private void fluctuateLikeCount(CreateLikeCommand cmd, Boolean isIncrease) {
        if (isIncrease == null) return;
        if (Objects.equals(cmd.videoType(), COMMENT_TYPE.getValue())) {
            // is Comment
            if (isIncrease) {
                commentWriteService.increaseLikeCount(cmd.commentId());
            } else {
                commentWriteService.decreaseLikeCount(cmd.commentId());
            }
        } else if (Objects.equals(cmd.videoType(), VIDEO_TYPE.getValue())) {
            // is Video
            if (isIncrease) {
                videoWriteService.increaseLikeCount(cmd.videoId());
            } else {
                videoWriteService.decreaseLikeCount(cmd.videoId());
            }
        } else {
            // is Shorts
            if (isIncrease) {
                shortWriteService.increaseLikeCount(cmd.videoId());
            } else {
                shortWriteService.decreaseLikeCount(cmd.videoId());
            }
        }
    }
}
