package com.pellto.youtoy.comment.application.adapter.out.persistence.reply;

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
@Table(name = "reply")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReplyEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long parentCommentId;
  private LocalDateTime createdAt;
  private Long replierId;
  private String replierHandle;
  private String replierDisplayName;
  private String replierProfilePath;
  private String content;
  private Long likeCount;
  private boolean ownerLike;
  private LocalDateTime updatedAt;
}
