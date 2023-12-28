package com.pellto.youtoy.domain.deprecated.base;

import com.pellto.youtoy.global.util.General;
import com.pellto.youtoy.global.util.Temporal;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@MappedSuperclass
@NoArgsConstructor
public abstract class Interest {

  @Column(name = "created_at")
  protected LocalDateTime createdAt;
  @Column(name = "dislike")
  protected boolean dislike;

  protected void changeDislike(boolean b) {
    this.dislike = b;
  }

  protected Interest(boolean dislike, LocalDateTime createdAt) {
    this.dislike = General.setNullInput(dislike, false);
    this.createdAt = Temporal.createdAt(createdAt);
  }
}
