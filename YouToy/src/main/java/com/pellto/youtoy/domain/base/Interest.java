package com.pellto.youtoy.domain.base;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@MappedSuperclass
@SuperBuilder
public abstract class Interest {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;
  @Column(name = "created_at")
  private LocalDateTime createdAt;
  @Column(name = "dislike")
  private boolean dislike;

  public void changeDislike(boolean b) {
    this.dislike = b;
  }

  public Interest(boolean dislike, LocalDateTime createdAt) {
    this.id = id;
    this.dislike = dislike;
    this.createdAt = createdAt;
  }
}
