package com.pellto.youtoy.comment.domain.model;

import com.pellto.youtoy.global.dto.comment.ReplyDto;
import com.pellto.youtoy.global.util.General;
import com.pellto.youtoy.global.util.Temporal;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Reply {

  private final Long id;
  private final Long parentCommentId;
  private final LocalDateTime createdAt;
  private CommenterInfo replierInfo;
  private String content;
  private Long likeCount;
  private boolean ownerLike;
  private LocalDateTime updatedAt;

  @Builder
  public Reply(Long id, Long parentCommentId, LocalDateTime createdAt, CommenterInfo replierInfo,
      String content, boolean ownerLike, Long likeCount, LocalDateTime updatedAt) {
    this.id = id;
    this.parentCommentId = Objects.requireNonNull(parentCommentId);
    this.createdAt = Temporal.createdAt(createdAt);
    this.replierInfo = Objects.requireNonNull(replierInfo);
    this.content = Objects.requireNonNull(content);
    this.likeCount = General.setNullInput(likeCount, 0L);
    this.ownerLike = General.setNullInput(ownerLike, false);
    this.updatedAt = General.setNullInput(updatedAt, this.createdAt);
  }

  public void changeContent(String content) {
    this.content = content;
    this.updatedAt = LocalDateTime.now();
  }

  public void pressOwnersLike() {
    this.ownerLike = true;
  }

  public void cancelOwnersLike() {
    this.ownerLike = false;
  }

  public void increaseLikeCount() {
    this.likeCount += 1;
  }

  public void decreaseLikeCount() {
    this.likeCount -= 1;
  }

  public ReplyDto toDto() {
    if (id == null) {
      throw new IllegalArgumentException("id는 필수 입니다.");
    }

    return ReplyDto.builder()
        .id(id)
        .parentCommentId(parentCommentId)
        .replierInfo(replierInfo)
        .content(content)
        .likeCount(likeCount)
        .ownerLike(ownerLike)
        .createdAt(createdAt)
        .updatedAt(updatedAt)
        .build();
  }
}
