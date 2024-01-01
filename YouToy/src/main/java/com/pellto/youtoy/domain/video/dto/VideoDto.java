package com.pellto.youtoy.domain.video.dto;

import java.time.LocalDateTime;

public record VideoDto(

    Long id,
    Long channelId,
    String title,
    String description,
    int commentCount,
    int likeCount,
    LocalDateTime createdAt
) {

}
