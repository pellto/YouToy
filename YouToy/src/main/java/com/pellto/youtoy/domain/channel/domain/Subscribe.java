package com.pellto.youtoy.domain.channel.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pellto.youtoy.global.util.General;
import com.pellto.youtoy.global.util.Temporal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "subscribe")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Subscribe {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "subscribe_id")
  private final Long id;
  @Column(name = "created_at", nullable = false)
  private final LocalDateTime createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  @JoinColumn(referencedColumnName = "channel_id", name = "subscriber_id")
  private final Channel subscriber;
  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  @JoinColumn(referencedColumnName = "channel_id", name = "subscribed_id")
  private final Channel subscribed;
  @Column(name = "level", nullable = false)
  @Enumerated(EnumType.STRING)
  private SubscribeLevel level;

  @Builder
  public Subscribe(Long id, LocalDateTime createdAt, Channel subscriber, Channel subscribed,
      SubscribeLevel level) {
    this.id = id;
    this.subscriber = subscriber;
    this.subscribed = subscribed;
    this.level = General.setNullInput(level, SubscribeLevel.CUSTOM);
    this.createdAt = Temporal.createdAt(createdAt);
  }

  public void changeLevel(SubscribeLevel level) {
    this.level = level;
  }
}
