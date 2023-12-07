package com.pellto.youtoy.util.comment;

import com.pellto.youtoy.domain.comment.dto.CreateCommentCommand;

public class CreateCommentCommandFixtureFactory {

  private static final Long VIDEO_ID = 1L;
  private static final Long USER_ID = 1L;
  private static final Long REPLIED_COMMENT_ID = 1L;
  private static final boolean VIDEO = true;
  private static final String CONTENT = "content";

  public static CreateCommentCommand create() {
    return create(VIDEO_ID, USER_ID, REPLIED_COMMENT_ID, VIDEO, CONTENT);
  }

  public static CreateCommentCommand create(boolean video) {
    return create(VIDEO_ID, USER_ID, REPLIED_COMMENT_ID, video, CONTENT);
  }

  public static CreateCommentCommand create(boolean video, String content) {
    return create(VIDEO_ID, USER_ID, REPLIED_COMMENT_ID, video, content);
  }

  public static CreateCommentCommand create(String content) {
    return create(VIDEO_ID, USER_ID, REPLIED_COMMENT_ID, VIDEO, content);
  }

  public static CreateCommentCommand create(
      Long videoId,
      Long userId,
      Long repliedCommentId,
      boolean video,
      String content
  ) {
    return new CreateCommentCommand(videoId, userId, video, content, repliedCommentId);
  }
}
