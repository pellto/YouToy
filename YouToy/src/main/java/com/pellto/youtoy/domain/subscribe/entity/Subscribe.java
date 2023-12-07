package com.pellto.youtoy.domain.subscribe.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Subscribe {

  private final Long id;
  private final Long channelId;
  private final Long userId;
  private final LocalDateTime createdAt;

  @Builder
  public Subscribe(Long id, Long channelId, Long userId, LocalDateTime createdAt) {
    this.id = id;
    this.channelId = Objects.requireNonNull(channelId);
    this.userId = Objects.requireNonNull(userId);
    this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
  }
}
