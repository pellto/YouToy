package com.pellto.youtoy.domain.deprecated.base;

import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@MappedSuperclass
@NoArgsConstructor
public abstract class CommentInterest extends Interest {

  public CommentInterest(boolean dislike, LocalDateTime createdAt) {
    super(dislike, createdAt);
  }
}
