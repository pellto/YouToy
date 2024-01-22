package com.pellto.youtoy.subscribe.application.adapter.out.persistence;

import com.pellto.youtoy.subscribe.domain.model.Subscribe;
import com.pellto.youtoy.subscribe.domain.model.SubscribedLevel;
import org.springframework.stereotype.Component;

@Component
public class SubscribeMapper {

  public Subscribe toDomain(SubscribeEntity entity) {
    SubscribedLevel subscribedLevel;
    switch (entity.getSubscribedLevel()) {
      case ("ALL") -> {
        subscribedLevel = SubscribedLevel.ALL;
      }
      case ("CUSTOM") -> {
        subscribedLevel = SubscribedLevel.CUSTOM;
      }
      case ("NONE") -> {
        subscribedLevel = SubscribedLevel.NONE;
      }
      default -> {
        throw new IllegalArgumentException();
      }
    }

    return Subscribe.builder()
        .id(entity.getId())
        .subscriberId(entity.getSubscriberId())
        .subscribedChannelId(entity.getSubscribedChannelId())
        .createdAt(entity.getCreatedAt())
        .subscribedLevel(subscribedLevel)
        .build();
  }

  public SubscribeEntity toEntity(Subscribe subscribe) {
    return SubscribeEntity.builder()
        .id(subscribe.getId())
        .subscriberId(subscribe.getSubscriberId())
        .subscribedChannelId(subscribe.getSubscribedChannelId())
        .subscribedLevel(subscribe.getSubscribedLevel().getValue())
        .createdAt(subscribe.getCreatedAt())
        .build();
  }

}
