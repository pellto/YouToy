package com.pellto.youtoy.subscribe.util;

import com.pellto.youtoy.global.dto.subscribe.request.SubscribeRequest;
import com.pellto.youtoy.global.dto.subscribe.request.UnsubscribeRequest;
import com.pellto.youtoy.subscribe.domain.model.Subscribe;
import com.pellto.youtoy.subscribe.domain.model.SubscribedLevel;
import java.time.LocalDateTime;

public class SubscribeFixtureFactory {

  private static final Long ID = 1L;
  private static final Long SUBSCRIBER_ID = 1L;
  private static final Long SUBSCRIBED_CHANNEL_ID = 1L;
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();
  private static final SubscribedLevel SUBSCRIBED_LEVEL = SubscribedLevel.CUSTOM;

  public static Subscribe create(Subscribe beforeSavedSubscribe) {
    return Subscribe.builder()
        .id(ID)
        .subscriberId(beforeSavedSubscribe.getSubscriberId())
        .subscribedChannelId(beforeSavedSubscribe.getSubscribedChannelId())
        .subscribedLevel(SUBSCRIBED_LEVEL)
        .createdAt(CREATED_AT)
        .build();
  }


  public static Subscribe createBeforeSaved() {
    return Subscribe.builder()
        .subscriberId(SUBSCRIBER_ID)
        .subscribedChannelId(SUBSCRIBED_CHANNEL_ID)
        .build();
  }

  public static Subscribe createBeforeSaved(SubscribeRequest req) {
    return Subscribe.builder()
        .subscriberId(req.subscriberId())
        .subscribedChannelId(req.subscribedChannelId())
        .build();
  }

  public static Subscribe create() {
    return Subscribe.builder()
        .id(ID)
        .subscriberId(SUBSCRIBER_ID)
        .subscribedChannelId(SUBSCRIBED_CHANNEL_ID)
        .subscribedLevel(SUBSCRIBED_LEVEL)
        .createdAt(CREATED_AT)
        .build();
  }

  public static Subscribe create(SubscribeRequest request) {
    return Subscribe.builder()
        .id(ID)
        .subscriberId(request.subscriberId())
        .subscribedChannelId(request.subscribedChannelId())
        .subscribedLevel(SUBSCRIBED_LEVEL)
        .createdAt(CREATED_AT)
        .build();
  }

  public static Subscribe create(UnsubscribeRequest request) {
    return Subscribe.builder()
        .id(ID)
        .subscriberId(request.subscriberId())
        .subscribedChannelId(request.subscribedChannelId())
        .subscribedLevel(SUBSCRIBED_LEVEL)
        .createdAt(CREATED_AT)
        .build();
  }

  public static UnsubscribeRequest createUnsubscribeRequest() {
    return new UnsubscribeRequest(SUBSCRIBER_ID, SUBSCRIBED_CHANNEL_ID);
  }

  public static Subscribe createWithSubscribedChannelId(long subscribedChannelId) {
    return Subscribe.builder()
        .id(ID)
        .subscriberId(SUBSCRIBER_ID)
        .subscribedChannelId(subscribedChannelId)
        .subscribedLevel(SUBSCRIBED_LEVEL)
        .createdAt(CREATED_AT)
        .build();
  }

  public static Subscribe createWithSubscriberId(Long subscriberId) {
    return Subscribe.builder()
        .id(ID)
        .subscriberId(SUBSCRIBER_ID)
        .subscribedChannelId(SUBSCRIBED_CHANNEL_ID)
        .subscribedLevel(SUBSCRIBED_LEVEL)
        .createdAt(CREATED_AT)
        .build();
  }

  public static SubscribeRequest createSubscribeRequest() {
    return createSubscribeRequest(SUBSCRIBER_ID, SUBSCRIBED_CHANNEL_ID);
  }

  public static SubscribeRequest createSubscribeRequest(Long subscriberId,
      Long subscribedChannelId) {
    return new SubscribeRequest(subscriberId, subscribedChannelId);
  }
}
