package com.pellto.youtoy.domain.channel.dto;

public record CreateChannelCommand(
    Long ownerId,
    String displayName
) {

}
