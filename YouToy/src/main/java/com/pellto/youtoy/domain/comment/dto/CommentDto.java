package com.pellto.youtoy.domain.comment.dto;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;


@Getter
@ToString
public class CommentDto {
    private final Long id;
    private final Long userId;
    private final String content;
    private final Long replyCnt;
    private final LocalDateTime createdAt;

    public CommentDto(Long id, Long userId, String content, Long replyCnt, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.replyCnt = replyCnt == null ? 0 : replyCnt;
        this.createdAt = createdAt;
    }
}
