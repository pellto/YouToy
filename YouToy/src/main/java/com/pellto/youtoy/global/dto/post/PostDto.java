package com.pellto.youtoy.global.dto.post;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record PostDto(
    Long id,
    Long channelId,
    String title,
    String content,
    LocalDateTime createdAt,
    Long viewCount,
    Long likeCount,
    Long commentCount,
    LocalDateTime updatedAt
) {

}
