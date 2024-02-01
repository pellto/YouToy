package com.pellto.youtoy.comment.application.adapter.out.persistence.comment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comment")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long commenterId;
  private String commenterHandle;
  private String commenterDisplayName;
  private String commenterProfilePath;
  private Long contentsId;
  private String commentContentsType;
  private String content;
  private boolean ownerLike;
  private Long replyCount;
  private Long likeCount;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
