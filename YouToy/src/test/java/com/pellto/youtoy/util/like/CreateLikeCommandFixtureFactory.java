package com.pellto.youtoy.util.like;

import com.pellto.youtoy.domain.like.dto.CreateLikeCommand;

public class CreateLikeCommandFixtureFactory {

  private static final Long USER_ID = 1L;
  private static final Long VIDEO_ID = 1L;
  private static final Integer VIDEO_TYPE = 0;
  private static final Long COMMENT_ID = 1L;

  public static CreateLikeCommand create() {
    return create(USER_ID, VIDEO_ID, VIDEO_TYPE, COMMENT_ID);
  }

  public static CreateLikeCommand create(Integer videoType) {
    return create(USER_ID, VIDEO_ID, videoType, COMMENT_ID);
  }

  public static CreateLikeCommand create(Integer videoType, Long commentId) {
    return create(USER_ID, VIDEO_ID, videoType, commentId);
  }

  private static CreateLikeCommand create(
      Long userId,
      Long videoId,
      Integer videoType,
      Long commentId
  ) {
    return new CreateLikeCommand(userId, videoId, videoType, commentId);
  }
}
