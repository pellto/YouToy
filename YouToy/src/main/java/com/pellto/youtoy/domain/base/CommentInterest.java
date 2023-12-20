package com.pellto.youtoy.domain.base;

import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@MappedSuperclass
@SuperBuilder
public abstract class CommentInterest extends Interest {

  public CommentInterest(boolean dislike, LocalDateTime createdAt) {
    super(dislike, createdAt);
  }
}
