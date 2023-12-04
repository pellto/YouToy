package com.pellto.youtoy.domain.view.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
public class ViewHistory {
    private final Long id;
    private final Long userId;
    private final Long videoId;
    private final Integer videoType;
    private final Long lastViewAt; // second
    private final LocalDateTime createdAt;

    @Builder
    public ViewHistory(Long id, Long userId, Long videoId, Integer videoType, Long lastViewAt, LocalDateTime createdAt) {
        this.id = id;
        this.userId = Objects.requireNonNull(userId);
        this.videoId = Objects.requireNonNull(videoId);
        this.videoType = Objects.requireNonNull(videoType);
        this.lastViewAt = lastViewAt == null ? -1 : lastViewAt;
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }
}
