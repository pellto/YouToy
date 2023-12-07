package com.pellto.youtoy.domain.comment.dto;

import java.time.LocalDateTime;

public record MentionDto(
    Long id,
    Long commentId,
    Long mentionedChannelId,
    LocalDateTime createdAt
) {

}
