package com.pellto.youtoy.util.channel;

import com.pellto.youtoy.domain.channel.dto.DeleteChannelAdminCommand;

public class DeleteChannelAdminCommandFixtureFactory {

  private static final Long CHANNEL_ID = 1L;
  private static final Long USER_ID = 1L;
  private static final Long OWNER_ID = 2L;

  public static DeleteChannelAdminCommand create() {
    return create(CHANNEL_ID, USER_ID, OWNER_ID);
  }

  public static DeleteChannelAdminCommand create(
      Long channelId,
      Long userId,
      Long ownerId) {
    return new DeleteChannelAdminCommand(channelId, userId, ownerId);
  }
}
