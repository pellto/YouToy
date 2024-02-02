package com.pellto.youtoy.video.domain.model;

import com.pellto.youtoy.global.dto.video.VideoEncodedInfoDto;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;

@Getter
public class VideoEncodedInfo {

  private Long runningTimeMs;
  private EncodedState encodedState;
  private final String fileName;
  private final String filePath;
  private final String thumbnailPath;
  private final String thumbnailFileName;
  private final LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  @Builder
  public VideoEncodedInfo(Long runningTimeMs, EncodedState encodedState, String fileName,
      String filePath, String thumbnailPath, String thumbnailFileName, LocalDateTime createdAt,
      LocalDateTime updatedAt) {
    this.runningTimeMs = Objects.requireNonNull(runningTimeMs);
    this.encodedState = Objects.requireNonNull(encodedState);
    this.fileName = Objects.requireNonNull(fileName);
    this.filePath = Objects.requireNonNull(filePath);
    this.thumbnailPath = Objects.requireNonNull(thumbnailPath);
    this.thumbnailFileName = Objects.requireNonNull(thumbnailFileName);
    this.createdAt = Objects.requireNonNull(createdAt);
    this.updatedAt = Objects.requireNonNull(updatedAt);
  }

  public VideoEncodedInfo(String fileName) {
    this.runningTimeMs = 0L;
    this.encodedState = EncodedState.PREPARING;
    this.fileName = fileName;
    this.filePath = "DEFAULT_PATH";
    this.thumbnailPath = "DEFAULT_THUMBNAIL_PATH";
    this.thumbnailFileName = "DEFAULT_THUMBNAIL_FILE_NAME";
    this.createdAt = LocalDateTime.now();
    this.updatedAt = this.createdAt;
  }

  public VideoEncodedInfoDto toDto() {
    return VideoEncodedInfoDto.builder()
        .runningTimeMs(runningTimeMs)
        .encodedState(encodedState)
        .fileName(fileName)
        .filePath(filePath)
        .thumbnailPath(thumbnailPath)
        .thumbnailFileName(thumbnailFileName)
        .createdAt(createdAt)
        .updatedAt(updatedAt)
        .build();

  }

  // TODO: state 별 검증 및 작동 확인하기
  // TODO: Video 도메인 로직으로 변경
  public VideoEncodedInfo changeEncodedState(EncodedState newState) {
    if (newState == EncodedState.ENCODED) {
      // TODO: 정확한 값으로 변경
      this.runningTimeMs = 100L;
    }
    this.encodedState = newState;
    this.updatedAt = LocalDateTime.now();
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VideoEncodedInfo that = (VideoEncodedInfo) o;
    return Objects.equals(runningTimeMs, that.runningTimeMs)
        && encodedState == that.encodedState && Objects.equals(fileName, that.fileName)
        && Objects.equals(filePath, that.filePath) && Objects.equals(
        thumbnailPath, that.thumbnailPath) && Objects.equals(thumbnailFileName,
        that.thumbnailFileName) && Objects.equals(createdAt, that.createdAt)
        && Objects.equals(updatedAt, that.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(runningTimeMs, encodedState, fileName, filePath, thumbnailPath,
        thumbnailFileName, createdAt, updatedAt);
  }
}
