package com.pellto.youtoy.domain.comment.dto;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;


public record CommentDto(
        Long id,
        Long userId,
        String content,
        Long replyCnt,
        LocalDateTime createdAt
) {
}
