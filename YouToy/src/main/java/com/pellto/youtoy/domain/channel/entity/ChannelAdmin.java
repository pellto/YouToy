package com.pellto.youtoy.domain.channel.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
public class ChannelAdmin {
    private final Long id;
    private final Long channelId;
    private final Long userId;
    private final LocalDateTime createdAt;

    @Builder
    public ChannelAdmin(Long id, Long channelId, Long userId, LocalDateTime createdAt) {
        this.id = id;
        this.channelId = Objects.requireNonNull(channelId);
        this.userId = Objects.requireNonNull(userId);
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }
}
