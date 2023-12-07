package com.pellto.youtoy.domain.playlist.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Playlist {

  private final Long id;
  private final Long channelId;
  private final LocalDateTime createdAt;

  private Integer targetRange;
  private String title;

  @Builder
  public Playlist(Long id, Long channelId, Integer targetRange, LocalDateTime createdAt,
      String title) {
    this.id = id;
    this.channelId = Objects.requireNonNull(channelId);
    this.targetRange = Objects.requireNonNull(targetRange);
    this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    this.title = title == null ? "" : title;
  }
}
