package com.pellto.youtoy.domain.channel.dto;

import javax.validation.constraints.NotNull;

public record CreateChannelAdminCommand(
    @NotNull
    Long channelId,
    @NotNull
    Long userId,
    @NotNull
    Long ownerId
) {

}
