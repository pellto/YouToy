package com.pellto.youtoy.util.channel;

import com.pellto.youtoy.domain.channel.dto.CreateChannelAdminCommand;
import com.pellto.youtoy.domain.channel.dto.DeleteChannelAdminCommand;
import com.pellto.youtoy.domain.channel.entity.ChannelAdmin;

import java.time.LocalDateTime;

public class ChannelAdminFixtureFactory {
    private static final Long ID = 1L;
    private static final Long CHANNEL_ID = 1L;
    private static final Long USER_ID = 1L;
    private static final LocalDateTime CREATED_AT = LocalDateTime
            .of(2023, 1, 1, 0, 0, 0);

    static public ChannelAdmin create() {
        return create(ID, CHANNEL_ID, USER_ID, CREATED_AT);
    }

    static public ChannelAdmin create(CreateChannelAdminCommand cmd) {
        return create(ID, cmd.channelId(), cmd.userId(), CREATED_AT);
    }

    static public ChannelAdmin create(Long id, Long channelId, Long userId, LocalDateTime createdAt) {
        return ChannelAdmin.builder()
                .id(id)
                .channelId(channelId)
                .userId(userId)
                .createdAt(createdAt)
                .build();
    }

    public static ChannelAdmin create(DeleteChannelAdminCommand cmd) {
        return create(ID, cmd.channelId(), cmd.userId(), CREATED_AT);
    }
}
