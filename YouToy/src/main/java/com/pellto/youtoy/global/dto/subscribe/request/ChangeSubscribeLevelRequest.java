package com.pellto.youtoy.global.dto.subscribe.request;

public record ChangeSubscribeLevelRequest(
    Long subscriberId,
    Long subscribedChannelId,
    String subscribeLevel
) {

}
