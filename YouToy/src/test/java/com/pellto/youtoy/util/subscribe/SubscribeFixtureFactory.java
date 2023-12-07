package com.pellto.youtoy.util.subscribe;

import com.pellto.youtoy.domain.subscribe.entity.Subscribe;

public class SubscribeFixtureFactory {

  private static final Long USER_ID = 1L;
  private static final Long CHANNEL_ID = 1L;

  static public Subscribe create() {
    return create(USER_ID, CHANNEL_ID);
  }

  static public Subscribe create(Long userId, Long channelId) {
    return Subscribe.builder()
        .channelId(userId)
        .userId(channelId)
        .build();
  }
}
