package com.pellto.youtoy.util.channel;

import com.pellto.youtoy.domain.channel.dto.CreateChannelCommand;

public class CreateChannelCommandFixtureFactory {

  private static final Long OWNER_ID = 1L;
  private static final String DISPLAY_NAME = "test";

  public static CreateChannelCommand create() {
    return new CreateChannelCommand(OWNER_ID, DISPLAY_NAME);
  }

  public static CreateChannelCommand createWithDisplayName(String displayName) {
    return new CreateChannelCommand(OWNER_ID, displayName);
  }

  public static CreateChannelCommand createWithOwnerId(Long ownerId) {
    return new CreateChannelCommand(ownerId, DISPLAY_NAME);
  }
}
