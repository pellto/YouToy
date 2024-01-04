package com.pellto.youtoy.domain.base;

import com.pellto.youtoy.global.util.General;
import com.pellto.youtoy.global.util.Temporal;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;


@MappedSuperclass
@Getter
@NoArgsConstructor
public abstract class BaseComment {

  @Column(name = "comment_content")
  protected String commentContent;
  @Column(name = "modified")
  protected boolean modified;
  @Column(name = "created_at")
  protected LocalDateTime createdAt;
  @Column(name = "modified_at")
  protected LocalDateTime modifiedAt;

  protected BaseComment(String commentContent, boolean modified,
      LocalDateTime createdAt,
      LocalDateTime modifiedAt) {
    this.modified = General.setNullInput(modified, false);
    this.commentContent = Objects.requireNonNull(commentContent);
    this.createdAt = Temporal.createdAt(createdAt);
    this.modifiedAt = General.setNullInput(modifiedAt, this.createdAt);
  }

  protected void changeCommentContent(String s) {
    this.commentContent = s;
    this.modified = true;
    this.modifiedAt = LocalDateTime.now();
  }
}
