package com.pellto.youtoy.util.comment;

import com.pellto.youtoy.domain.comment.dto.CreateMentionCommand;
import java.time.LocalDateTime;

public class CreateMentionCommandFixtureFactory {

  private static final Long COMMENT_ID = 1L;
  private static final Long MENTIONED_CHANNEL_ID = 1L;
  private static final LocalDateTime CREATED_AT = LocalDateTime.of(
      2023, 1, 1, 0, 0, 0
  );

  public static CreateMentionCommand create() {
    return create(COMMENT_ID, MENTIONED_CHANNEL_ID, CREATED_AT);
  }

  public static CreateMentionCommand create(
      Long commentId,
      Long mentionedChannelId,
      LocalDateTime createdAt
  ) {
    return new CreateMentionCommand(commentId, mentionedChannelId, createdAt);
  }
}
