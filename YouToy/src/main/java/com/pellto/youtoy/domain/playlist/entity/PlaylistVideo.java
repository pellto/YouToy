package com.pellto.youtoy.domain.playlist.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PlaylistVideo {

  private final Long id;
  private final Long playlistId;
  private final Long videoId;
  private final Integer videoType;
  private final LocalDateTime createdAt;

  @Builder
  public PlaylistVideo(Long id, Long playlistId, Long videoId, Integer videoType,
      LocalDateTime createdAt) {
    this.id = id;
    this.playlistId = Objects.requireNonNull(playlistId);
    this.videoId = Objects.requireNonNull(videoId);
    this.videoType = Objects.requireNonNull(videoType);
    this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
  }
}
