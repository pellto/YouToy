package com.pellto.youtoy.domain.view.dto;

import java.time.LocalDateTime;

public class VideoDto extends VideoContentsDto {
    public VideoDto(Long id, Long channelId, String title, Long viewCount, String description, LocalDateTime createdAt, Long likeCount, boolean isVideo) {
        super(id, channelId, title, viewCount, description, createdAt, likeCount, isVideo);
    }
}
