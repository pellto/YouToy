package com.pellto.youtoy.domain.like.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Like {

  private final Long id;
  private final Long userId;
  private final LocalDateTime createdAt;
  private final Long videoId;
  private final Integer videoType;
  private final Long commentId;

  @Builder
  public Like(
      Long id,
      Long userId,
      LocalDateTime createdAt,
      Long videoId,
      Integer videoType,
      Long commentId
  ) {
    this.id = id;
    this.userId = Objects.requireNonNull(userId);
    this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    this.videoId = videoId;
    this.videoType = videoType;
    this.commentId = commentId;
  }
}
