package com.pellto.youtoy.comment.domain.model;

import com.pellto.youtoy.global.dto.comment.CommentDto;
import com.pellto.youtoy.global.util.General;
import com.pellto.youtoy.global.util.Temporal;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Comment {

  private final Long id;
  private final Long contentsId;
  private final CommentContentsType commentContentsType;
  private final LocalDateTime createdAt;
  private CommenterInfo commenterInfo;
  private String content;
  private boolean ownerLike;
  private Long replyCount;
  private Long likeCount;
  private LocalDateTime updatedAt;


  @Builder
  public Comment(Long id, CommenterInfo commenterInfo, Long contentsId, String commentContentsType,
      LocalDateTime createdAt, String content, boolean ownerLike, Long replyCount, Long likeCount,
      LocalDateTime updatedAt) {
    this.id = id;
    this.commenterInfo = Objects.requireNonNull(commenterInfo);
    this.contentsId = Objects.requireNonNull(contentsId);
    this.commentContentsType = CommentContentsType.fromString(
        Objects.requireNonNull(commentContentsType));
    this.createdAt = Temporal.createdAt(createdAt);
    this.content = Objects.requireNonNull(content);
    this.ownerLike = General.setNullInput(ownerLike, false);
    this.replyCount = General.setNullInput(replyCount, 0L);
    this.likeCount = General.setNullInput(likeCount, 0L);
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

  public void increaseReplyCount() {
    this.replyCount += 1;
  }

  public void decreaseReplyCount() {
    this.replyCount -= 1;
  }

  public CommentDto toDto() {
    if (id == null) {
      throw new IllegalArgumentException("id 는 필수");
    }
    return CommentDto.builder()
        .id(id)
        .commenterInfo(commenterInfo)
        .contentsId(contentsId)
        .commentContentsType(commentContentsType.getType())
        .content(content)
        .ownerLike(ownerLike)
        .replyCount(replyCount)
        .likeCount(likeCount)
        .createdAt(createdAt)
        .updatedAt(updatedAt)
        .build();
  }

  public void changeCommentInfo(String commenterHandle, String commenterDisplayName,
      String commenterProfilePath) {
    this.commenterInfo = CommenterInfo.builder()
        .commenterId(this.commenterInfo.commenterId())
        .commenterHandle(commenterHandle)
        .displayName(commenterDisplayName)
        .profilePath(commenterProfilePath)
        .build();
  }
}
