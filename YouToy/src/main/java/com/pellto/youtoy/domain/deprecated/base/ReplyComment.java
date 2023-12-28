package com.pellto.youtoy.domain.deprecated.base;

import com.pellto.youtoy.global.util.General;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@MappedSuperclass
@NoArgsConstructor
public abstract class ReplyComment extends Comment {

  private boolean mentioned;

  public ReplyComment(Long likeCount, String content, boolean modified, LocalDateTime createdAt,
      LocalDateTime modifiedAt, boolean mentioned) {
    super(likeCount, content, modified, createdAt, modifiedAt);
    this.mentioned = General.setNullInput(mentioned, false);
  }
}
