package com.pellto.youtoy.domain.base;

import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class ReplyComment extends Comment {

  private boolean mentioned;

  public ReplyComment(Long likeCount, String content, boolean modified, LocalDateTime createdAt,
      LocalDateTime modifiedAt, boolean mentioned) {
    super(likeCount, content, modified, createdAt, modifiedAt);
    this.mentioned = mentioned;
  }
}
