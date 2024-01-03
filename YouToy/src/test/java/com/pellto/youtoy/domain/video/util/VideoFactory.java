package com.pellto.youtoy.domain.video.util;

import com.pellto.youtoy.domain.video.domain.Video;
import com.pellto.youtoy.domain.video.dto.VideoDto;
import com.pellto.youtoy.domain.video.dto.VideoUploadRequest;
import java.time.LocalDateTime;

public class VideoFactory {

  private static final Long ID = 1L;
  private static final Long CHANNEL_ID = 1L;
  private static final String TITLE = "test-title";
  private static final Long VIEW_COUNT = 0L;
  private static final String DESCRIPTION = "test-description";
  private static final int LIKE_COUNT = 0;
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();
  private static final LocalDateTime MODIFIED_AT = CREATED_AT;

  public static Video create() {
    return Video.builder()
        .id(ID)
        .channelId(CHANNEL_ID)
        .title(TITLE)
        .viewCount(VIEW_COUNT)
        .description(DESCRIPTION)
        .likeCount(LIKE_COUNT)
        .createdAt(CREATED_AT)
        .modifiedAt(MODIFIED_AT)
        .build();
  }

  public static Video create(VideoUploadRequest req) {
    return Video.builder()
        .id(ID)
        .channelId(req.channelId())
        .title(req.title())
        .viewCount(VIEW_COUNT)
        .description(req.description())
        .likeCount(LIKE_COUNT)
        .createdAt(CREATED_AT)
        .modifiedAt(MODIFIED_AT)
        .build();
  }

  public static Video create(Long id) {
    return Video.builder()
        .id(id)
        .channelId(CHANNEL_ID)
        .title(TITLE)
        .viewCount(VIEW_COUNT)
        .description(DESCRIPTION)
        .likeCount(LIKE_COUNT)
        .createdAt(CREATED_AT)
        .modifiedAt(MODIFIED_AT)
        .build();
  }

  public static Video createBeforeSaved() {
    return Video.builder()
        .channelId(CHANNEL_ID)
        .title(TITLE)
        .description(DESCRIPTION)
        .build();
  }

  public static VideoDto createDto(Video video) {
    return new VideoDto(video.getId(), video.getChannelId(), video.getTitle(),
        video.getDescription(), video.getCommentCount(), video.getLikeCount(),
        video.getCreatedAt());
  }

  public static VideoUploadRequest createUploadRequest() {
    return new VideoUploadRequest(CHANNEL_ID, TITLE, DESCRIPTION);
  }
}
