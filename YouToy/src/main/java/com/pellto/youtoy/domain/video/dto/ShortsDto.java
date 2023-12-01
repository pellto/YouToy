package com.pellto.youtoy.domain.video.dto;

import java.time.LocalDateTime;

public class ShortsDto extends VideoContentsDto {
    public ShortsDto(
            Long id,
            Long channelId,
            String title,
            Long viewCount,
            String description,
            LocalDateTime createdAt,
            Long likeCount,
            boolean isVideo
    ) {
        super(id, channelId, title, viewCount, description, createdAt, likeCount, isVideo);
    }
}
