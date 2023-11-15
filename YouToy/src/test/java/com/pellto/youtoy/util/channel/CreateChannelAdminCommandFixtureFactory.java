package com.pellto.youtoy.util.channel;

import com.pellto.youtoy.domain.channel.dto.CreateChannelAdminCommand;

public class CreateChannelAdminCommandFixtureFactory {
    private static final Long CHANNEL_ID = 1L;
    private static final Long USER_ID = 1L;
    private static final Long OWNER_ID = 2L;

    public static CreateChannelAdminCommand create() {
        return create(CHANNEL_ID, USER_ID, OWNER_ID);
    }

    public static CreateChannelAdminCommand create(
            Long channelId,
            Long userId,
            Long ownerId) {
        return new CreateChannelAdminCommand(channelId, userId, ownerId);
    }
}
