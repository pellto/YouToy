package com.pellto.youtoy.domain.channel.dto;

public record CreateSubscribeRelRequest(
    Long subscriberId,
    Long subscribedId
) {

}
