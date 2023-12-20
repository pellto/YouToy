package com.pellto.youtoy.domain.base;

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
  private Long likeCount;
  @Column(name = "content")
  private String content;
  @Column(name = "modified")
  private boolean modified;
  @Column(name = "created_at")
  private LocalDateTime createdAt;
  @Column(name = "modified_at")
  private LocalDateTime modifiedAt;

  public Comment(Long likeCount, String content, boolean modified, LocalDateTime createdAt,
      LocalDateTime modifiedAt) {
    this.likeCount = Numeric.initCount(likeCount);
    this.modified = modified;
    this.content = Objects.requireNonNull(content);
    this.createdAt = Temporal.createdAt(createdAt);
    this.modifiedAt = Temporal.createdAt(modifiedAt);
  }

  public void increaseLikeCount() {
    this.likeCount += 1;
  }

  public void decreaseLikeCount() {
    this.likeCount -= 1;
  }

  public String changeContent(String s) {
    this.content = s;
    return this.content;
  }
}
