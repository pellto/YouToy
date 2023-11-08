package com.pellto.youtoy.domain.subscribe.dto;

public record CreateSubscribeCommand(
        Long channelId,
        Long userId
) {
}
