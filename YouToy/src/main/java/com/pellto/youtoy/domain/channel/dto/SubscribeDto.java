package com.pellto.youtoy.domain.channel.dto;

public record SubscribeDto(
    Long id,
    Long subscriberId,
    Long subscribedId
) {

}
