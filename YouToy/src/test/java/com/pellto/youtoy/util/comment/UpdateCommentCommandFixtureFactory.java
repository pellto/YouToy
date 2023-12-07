package com.pellto.youtoy.util.comment;

import com.pellto.youtoy.domain.comment.dto.UpdateCommentCommand;

public class UpdateCommentCommandFixtureFactory {

  private static final Long ID = 1L;
  private static final String CONTENT = "updated_content";

  public static UpdateCommentCommand create() {
    return create(ID, CONTENT);
  }

  public static UpdateCommentCommand create(Long id, String content) {
    return new UpdateCommentCommand(id, content);
  }

  public static UpdateCommentCommand create(String content) {
    return new UpdateCommentCommand(ID, content);
  }

  public static UpdateCommentCommand create(Long id) {
    return new UpdateCommentCommand(id, CONTENT);
  }
}
