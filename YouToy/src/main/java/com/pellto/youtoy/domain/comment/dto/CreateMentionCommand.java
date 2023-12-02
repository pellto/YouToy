package com.pellto.youtoy.domain.comment.dto;

import java.time.LocalDateTime;

public record CreateMentionCommand(
        Long commentId,
        Long mentionedChannelId,
        LocalDateTime createdAt
) {
}
