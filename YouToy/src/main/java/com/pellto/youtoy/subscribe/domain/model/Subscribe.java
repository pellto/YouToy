package com.pellto.youtoy.subscribe.domain.model;

import com.pellto.youtoy.global.dto.subscribe.SubscribeDto;
import com.pellto.youtoy.global.util.General;
import com.pellto.youtoy.global.util.Temporal;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Subscribe {

  private final Long id;
  private final Long subscriberId;
  private final Long subscribedChannelId;
  private final LocalDateTime createdAt;
  private SubscribedLevel subscribedLevel;

  @Builder
  public Subscribe(Long id, Long subscriberId, Long subscribedChannelId, LocalDateTime createdAt,
      SubscribedLevel subscribedLevel) {
    this.id = id;
    this.subscriberId = Objects.requireNonNull(subscriberId);
    this.subscribedChannelId = Objects.requireNonNull(subscribedChannelId);
    this.createdAt = Temporal.createdAt(createdAt);
    this.subscribedLevel = General.setNullInput(subscribedLevel, SubscribedLevel.CUSTOM);
  }

  public void changeLevel(String level) {
    switch (level) {
      case ("ALL") -> {
        this.subscribedLevel = SubscribedLevel.ALL;
      }
      case ("CUSTOM") -> {
        this.subscribedLevel = SubscribedLevel.CUSTOM;
      }
      case ("NONE") -> {
        this.subscribedLevel = SubscribedLevel.NONE;
      }
      default -> {
        throw new IllegalArgumentException();
      }
    }
  }

  public SubscribeDto toDto() {
    return new SubscribeDto(
        this.id,
        this.subscriberId,
        this.subscribedChannelId,
        this.subscribedLevel.getValue(),
        this.createdAt
    );
  }
}
