package com.pellto.youtoy.util.comment;

import com.pellto.youtoy.domain.comment.dto.CreateCommentCommand;
import com.pellto.youtoy.domain.comment.dto.UpdateCommentCommand;
import com.pellto.youtoy.domain.comment.entity.Comment;
import java.time.LocalDateTime;

public class CommentFixtureFactory {

  private static final Long ID = 1L;
  private static final Long VIDEO_ID = 1L;
  private static final Long USER_ID = 1L;
  private static final Long REPLIED_COMMENT_ID = 2L;
  private static final LocalDateTime CREATED_AT = LocalDateTime
      .of(2023, 1, 1, 0, 0, 0);
  private static final boolean VIDEO = true;
  private static final String CONTENT = "content";

  public static Comment create() {
    return create(ID, VIDEO_ID, USER_ID, REPLIED_COMMENT_ID, CREATED_AT, VIDEO, CONTENT);
  }

  public static Comment create(String content) {
    return create(ID, VIDEO_ID, USER_ID, REPLIED_COMMENT_ID, CREATED_AT, VIDEO, content);
  }

  public static Comment create(CreateCommentCommand cmd) {
    return create(ID, cmd.videoId(), cmd.userId(), cmd.repliedCommentId(), CREATED_AT, cmd.video(),
        cmd.content());
  }

  public static Comment create(
      Long id,
      Long videoId,
      Long userId,
      Long repliedCommentId,
      LocalDateTime createdAt,
      boolean video,
      String content
  ) {
    return Comment.builder()
        .id(id)
        .videoId(videoId)
        .userId(userId)
        .repliedCommentId(repliedCommentId)
        .createdAt(createdAt)
        .video(video)
        .content(content)
        .build();
  }

  public static Comment create(UpdateCommentCommand cmd) {
    return create(cmd.id(), VIDEO_ID, USER_ID, REPLIED_COMMENT_ID, CREATED_AT, VIDEO,
        cmd.content());
  }
}
