package com.pellto.youtoy.util.like;

import com.pellto.youtoy.domain.like.entity.Dislike;

import java.time.LocalDateTime;

public class DislikeFixtureFactory {
    private static final Long ID = 1L;
    private static final Long USER_ID = 1L;
    private static final LocalDateTime CREATED_AT = LocalDateTime.of(
            2023, 1, 1, 0, 0, 0);
    private static final Long VIDEO_ID = 1L;
    private static final Integer VIDEO_TYPE = 0;
    private static final Long COMMENT_ID = 1L;

    public static Dislike create() {
        return create(ID, VIDEO_ID, VIDEO_TYPE, COMMENT_ID, USER_ID, CREATED_AT);
    }

    private static Dislike create(
            Long id,
            Long videoId,
            Integer videoType,
            Long commentId,
            Long userId,
            LocalDateTime createdAt
    ) {
        return Dislike.builder()
                .id(id)
                .videoId(videoId)
                .videoType(videoType)
                .commentId(commentId)
                .userId(userId)
                .createdAt(createdAt)
                .build();
    }
}
