package com.pellto.youtoy.domain.subscribe.dto;

public record CancelSubscribeCommand(
    Long channelId,
    Long userId
) {

}
