package com.pellto.youtoy.domain.base;

import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@MappedSuperclass
@NoArgsConstructor
public abstract class ReplyCommentInterest extends Interest {

  public ReplyCommentInterest(boolean dislike, LocalDateTime createdAt) {
    super(dislike, createdAt);
  }
}
