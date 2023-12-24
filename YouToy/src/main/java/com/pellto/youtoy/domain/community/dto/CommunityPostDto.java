package com.pellto.youtoy.domain.community.dto;

import java.time.LocalDateTime;

public record CommunityPostDto(
    Long id,
    Long channelId,
    String content,
    boolean modified,
    int commentCount,
    LocalDateTime createdAt
) {

}
