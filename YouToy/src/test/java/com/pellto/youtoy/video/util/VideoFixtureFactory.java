package com.pellto.youtoy.video.util;

import com.pellto.youtoy.global.dto.video.request.UploadVideoRequest;
import com.pellto.youtoy.video.domain.model.EncodedState;
import com.pellto.youtoy.video.domain.model.Video;
import com.pellto.youtoy.video.domain.model.VideoDetailInfo;
import com.pellto.youtoy.video.domain.model.VideoEncodedInfo;
import java.time.LocalDateTime;

public class VideoFixtureFactory {

  private static final Long ID = 1L;
  private static final Long CHANNEL_ID = 1L;
  private static final String ENCODING_REQUEST_ID = "test-encoding-request-id";
  private static final String TITLE = "test title";
  private static final String DESCRIPTION = "test description";
  private static final String HASH_TAGS = "#test#tags";
  private static final Long LIKE_COUNT = 0L;
  private static final Long VIEW_COUNT = 0L;
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();
  private static final LocalDateTime UPDATED_AT = CREATED_AT;
  private static final Long RUNNING_TIMES_MS = 0L;
  private static final EncodedState ENCODED_STATE = EncodedState.PREPARING;
  private static final String FILE_NAME = "test_file_name";
  private static final String FILE_PATH = "test_file_path";
  private static final String THUMBNAIL_PATH = "test_thumbnail_path";
  private static final String THUMBNAIL_FILE_NAME = "test_thumbnail_file_name";
  private static final LocalDateTime ENCODING_INFO_CREATED_AT = LocalDateTime.now();
  private static final LocalDateTime ENCODING_INFO_UPDATED_AT = ENCODING_INFO_CREATED_AT;

  public static UploadVideoRequest createUploadVideoRequest() {
    return new UploadVideoRequest(CHANNEL_ID, TITLE, DESCRIPTION, HASH_TAGS);
  }

  public static VideoDetailInfo createVideoDetailInfo() {
    return VideoDetailInfo.builder()
        .title(TITLE)
        .description(DESCRIPTION)
        .hashTags(HASH_TAGS)
        .build();
  }

  public static Video createBeforeSaved() {
    return Video.builder()
        .channelId(CHANNEL_ID)
        .videoDetailInfo(createVideoDetailInfo())
        .build();
  }

  public static Video create() {
    return Video.builder()
        .id(ID)
        .channelId(CHANNEL_ID)
        .createdAt(CREATED_AT)
        .likeCount(LIKE_COUNT)
        .viewCount(VIEW_COUNT)
        .updatedAt(UPDATED_AT)
        .videoDetailInfo(createVideoDetailInfo())
        .videoEncodedInfo(createVideoEncodedInfo())
        .build();
  }

  public static VideoEncodedInfo createVideoEncodedInfo() {
    return VideoEncodedInfo.builder()
        .runningTimeMs(RUNNING_TIMES_MS)
        .encodedState(ENCODED_STATE)
        .fileName(FILE_NAME)
        .filePath(FILE_PATH)
        .thumbnailPath(THUMBNAIL_PATH)
        .thumbnailFileName(THUMBNAIL_FILE_NAME)
        .createdAt(ENCODING_INFO_CREATED_AT)
        .updatedAt(ENCODING_INFO_UPDATED_AT)
        .build();

  }

}
