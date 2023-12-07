package com.pellto.youtoy.domain.channel.dto;

public record ChannelDto(
    Long id,
    Long ownerId,
    String handle,
    String displayName
) {

}
