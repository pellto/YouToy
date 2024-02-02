package com.pellto.youtoy.video.domain.model;

import com.pellto.youtoy.global.dto.video.VideoDto;
import com.pellto.youtoy.global.util.General;
import com.pellto.youtoy.global.util.RandomString;
import com.pellto.youtoy.global.util.Temporal;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Video {

  private final Long id;
  private final Long channelId;
  private final LocalDateTime createdAt;
  private final String encodingRequestId;
  private VideoDetailInfo videoDetailInfo;
  private Long likeCount;
  private Long viewCount;
  private LocalDateTime updatedAt;
  private VideoEncodedInfo videoEncodedInfo;


  @Builder
  public Video(Long id, Long channelId, LocalDateTime createdAt, VideoDetailInfo videoDetailInfo,
      Long likeCount, Long viewCount, LocalDateTime updatedAt, String encodingRequestId,
      VideoEncodedInfo videoEncodedInfo) {
    this.id = id;
    this.channelId = Objects.requireNonNull(channelId);
    this.videoDetailInfo = Objects.requireNonNull(videoDetailInfo);
    this.createdAt = Temporal.createdAt(createdAt);
    this.likeCount = General.setNullInput(likeCount, 0L);
    this.viewCount = General.setNullInput(viewCount, 0L);
    this.updatedAt = General.setNullInput(updatedAt, createdAt);
    this.encodingRequestId = General.setNullInput(encodingRequestId, createEncodingRequestId());
    this.videoEncodedInfo = General.setNullInput(videoEncodedInfo,
        new VideoEncodedInfo(createTitle())
    );
  }

  public void increaseLikeCount() {
    this.likeCount += 1;
  }

  public void decreaseLikeCount() {
    this.likeCount -= 1;
  }

  public void increaseViewCount() {
    this.viewCount += 1;
  }

  public void decreaseViewCount() {
    this.viewCount -= 1;
  }

  public VideoDetailInfo changeVideoDetailInfo(
      String newTitle,
      String newDescription,
      String newHashTags
  ) {
    var newDetailInfo = VideoDetailInfo.builder()
        .title(newTitle)
        .description(newDescription)
        .hashTags(newHashTags)
        .build();
    this.videoDetailInfo = newDetailInfo;
    this.updatedAt = LocalDateTime.now();
    return newDetailInfo;
  }

  public VideoDetailInfo changeVideoTitleInfo(String newTitle) {
    var newDetailInfo = VideoDetailInfo.builder()
        .title(newTitle)
        .description(videoDetailInfo.getDescription())
        .hashTags(videoDetailInfo.getHashTags())
        .build();
    this.videoDetailInfo = newDetailInfo;
    this.updatedAt = LocalDateTime.now();
    return newDetailInfo;
  }

  public VideoDetailInfo changeVideoDescriptionInfo(
      String newDescription
  ) {
    var newDetailInfo = VideoDetailInfo.builder()
        .title(videoDetailInfo.getTitle())
        .description(newDescription)
        .hashTags(videoDetailInfo.getHashTags())
        .build();
    this.videoDetailInfo = newDetailInfo;
    this.updatedAt = LocalDateTime.now();
    return newDetailInfo;
  }

  public VideoDetailInfo changeVideoHashTagsInfo(
      String newHashTags
  ) {
    var newDetailInfo = VideoDetailInfo.builder()
        .title(videoDetailInfo.getTitle())
        .description(videoDetailInfo.getDescription())
        .hashTags(newHashTags)
        .build();
    this.videoDetailInfo = newDetailInfo;
    this.updatedAt = LocalDateTime.now();
    return newDetailInfo;
  }

  public VideoEncodedInfo changeEncodedState(EncodedState newState) {
    this.videoEncodedInfo = this.videoEncodedInfo.changeEncodedState(newState);
    return this.videoEncodedInfo;
  }

  public VideoDto toDto() {
    return VideoDto.builder()
        .id(id)
        .channelId(channelId)
        .encodingRequestId(encodingRequestId)
        .likeCount(likeCount)
        .viewCount(viewCount)
        .createdAt(createdAt)
        .updatedAt(updatedAt)
        .videoDetailInfo(videoDetailInfo.toDto())
        .videoEncodedInfo(videoEncodedInfo.toDto())
        .build();
  }

  private String createEncodingRequestId() {
    return RandomString.make(128);
  }

  private String createRandomTail() {
    return RandomString.make(32);
  }

  private String createTitle() {
    return "%s-%s-%s".formatted(channelId, videoDetailInfo.getTitle(), createRandomTail());
  }
}
