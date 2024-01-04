package com.pellto.youtoy.domain.post.dto;

import java.time.LocalDateTime;

public record PostDto(
    Long id,
    Long channelId,
    String content,
    boolean modified,
    int commentCount,
    int likeCount,
    LocalDateTime createdAt
) {

}
