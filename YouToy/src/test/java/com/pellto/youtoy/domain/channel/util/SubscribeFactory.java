package com.pellto.youtoy.domain.channel.util;

import com.pellto.youtoy.domain.channel.domain.Channel;
import com.pellto.youtoy.domain.channel.domain.Subscribe;
import com.pellto.youtoy.domain.channel.dto.CreateSubscribeRelRequest;
import com.pellto.youtoy.domain.channel.dto.SubscribeDto;
import java.time.LocalDateTime;

public class SubscribeFactory {

  private static final Long ID = 1L;
  private static final Channel SUBSCRIBER = ChannelFactory.createSubscriber();
  private static final Channel SUBSCRIBED = ChannelFactory.createSubscribed();
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();


  public static Subscribe createSubscribe() {
    return Subscribe.builder()
        .id(ID)
        .subscriber(SUBSCRIBER)
        .subscribed(SUBSCRIBED)
        .createdAt(CREATED_AT)
        .build();
  }

  public static Subscribe createBeforeSavedSubscribe() {
    return Subscribe.builder()
        .subscriber(SUBSCRIBER)
        .subscribed(SUBSCRIBED)
        .createdAt(CREATED_AT)
        .build();
  }

  public static SubscribeDto createSubscribeDto() {
    return new SubscribeDto(ID, SUBSCRIBER.getId(), SUBSCRIBED.getId());
  }

  public static CreateSubscribeRelRequest createSubscribeRelRequest() {
    return new CreateSubscribeRelRequest(SUBSCRIBER.getId(), SUBSCRIBED.getId());
  }
}
