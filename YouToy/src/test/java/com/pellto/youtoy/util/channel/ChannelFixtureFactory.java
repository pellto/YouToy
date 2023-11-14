package com.pellto.youtoy.util.channel;

import com.pellto.youtoy.domain.channel.dto.CreateChannelCommand;
import com.pellto.youtoy.domain.channel.entity.Channel;

import java.time.LocalDateTime;

public class ChannelFixtureFactory {
    private static final Long TEST_ID = 1L;
    private static final LocalDateTime CREATED_AT = LocalDateTime.of(
            2023, 1, 1, 0, 0, 0
    );

    static public Channel create(CreateChannelCommand cmd) {
        return Channel.builder()
                .id(TEST_ID)
                .ownerId(cmd.ownerId())
                .displayName(cmd.displayName())
                .createdAt(CREATED_AT)
                .build();
    }
}
