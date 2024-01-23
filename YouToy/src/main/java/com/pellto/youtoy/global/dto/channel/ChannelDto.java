package com.pellto.youtoy.global.dto.channel;

public record ChannelDto(
    Long id,
    Long ownerId,
    String handle,
    ChannelInfoDto info,
    Long subscriberCount
) {

}
