package com.pellto.youtoy.domain.base;

import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@MappedSuperclass
@SuperBuilder
public abstract class ReplyCommentInterest extends Interest {

  public ReplyCommentInterest(boolean dislike, LocalDateTime createdAt) {
    super(dislike, createdAt);
  }
}
