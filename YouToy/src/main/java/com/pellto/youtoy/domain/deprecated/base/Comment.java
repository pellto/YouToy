package com.pellto.youtoy.domain.deprecated.base;

import com.pellto.youtoy.global.util.General;
import com.pellto.youtoy.global.util.Numeric;
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
public abstract class Comment {

  @Column(name = "like_count")
  protected Long likeCount;
  @Column(name = "content")
  protected String content;
  @Column(name = "modified")
  protected boolean modified;
  @Column(name = "created_at")
  protected LocalDateTime createdAt;
  @Column(name = "modified_at")
  protected LocalDateTime modifiedAt;

  protected Comment(Long likeCount, String content, boolean modified, LocalDateTime createdAt,
      LocalDateTime modifiedAt) {
    this.likeCount = Numeric.initCount(likeCount);
    this.modified = General.setNullInput(modified, false);
    this.content = Objects.requireNonNull(content);
    this.createdAt = Temporal.createdAt(createdAt);
    this.modifiedAt = Temporal.createdAt(modifiedAt);
  }

  protected String changeContent(String s) {
    this.content = s;
    this.modified = true;
    this.modifiedAt = LocalDateTime.now();
    return this.content;
  }
}
