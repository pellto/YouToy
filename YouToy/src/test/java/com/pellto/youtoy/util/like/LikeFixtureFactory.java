package com.pellto.youtoy.util.like;

import com.pellto.youtoy.domain.like.dto.LikeDto;
import com.pellto.youtoy.domain.like.entity.Like;

import java.time.LocalDateTime;

public class LikeFixtureFactory {
    private static final Long ID = 1L;
    private static final Long USER_ID = 1L;
    private static final LocalDateTime CREATED_AT = LocalDateTime.of(
            2023, 1, 1, 0, 0, 0);
    private static final Long VIDEO_ID = 1L;
    private static final Integer VIDEO_TYPE = 0;
    private static final Long COMMENT_ID = 1L;

    public static Like create() {
        return create(ID, VIDEO_ID, VIDEO_TYPE, COMMENT_ID, USER_ID, CREATED_AT);
    }

    public static Like create(Integer videoType) {
        return create(ID, VIDEO_ID, videoType, COMMENT_ID, USER_ID, CREATED_AT);
    }

    public static Like create(
            Long id,
            Long videoId,
            Integer videoType,
            Long commentId,
            Long userId,
            LocalDateTime createdAt
    ) {
        return Like.builder()
                .id(id)
                .videoId(videoId)
                .videoType(videoType)
                .commentId(commentId)
                .userId(userId)
                .createdAt(createdAt)
                .build();
    }

    public static LikeDto toDto(Like like) {
        return new LikeDto(
                like.getId(),
                like.getVideoId(),
                like.getVideoType(),
                like.getCommentId(),
                like.getUserId()
        );
    }
}
