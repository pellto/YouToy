package com.pellto.youtoy.global.dto.subscribe.request;

public record UnsubscribeRequest(
    Long subscriberId,
    Long subscribedChannelId
) {

}
