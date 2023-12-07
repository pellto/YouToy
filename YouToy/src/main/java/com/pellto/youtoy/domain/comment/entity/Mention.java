package com.pellto.youtoy.domain.comment.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Mention {

  private final Long id;
  private final Long commentId;
  private final Long mentionedChannelId;
  private final LocalDateTime createdAt;

  @Builder
  public Mention(Long id, Long commentId, Long mentionedChannelId, LocalDateTime createdAt) {
    this.id = id;
    this.commentId = Objects.requireNonNull(commentId);
    this.mentionedChannelId = Objects.requireNonNull(mentionedChannelId);
    this.createdAt = Objects.requireNonNull(createdAt);
  }

}
