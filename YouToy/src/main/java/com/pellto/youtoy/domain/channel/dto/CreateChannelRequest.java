package com.pellto.youtoy.domain.channel.dto;

public record CreateChannelRequest(
    String handle,
    String displayName,
    String description,
    String banner,
    String profile
) {

}
