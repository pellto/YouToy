package com.pellto.youtoy.domain.channel.dto;

import javax.validation.constraints.NotNull;

public record UpdateChannelCommand(
    @NotNull(message = "Channel Id는 필수입니다.")
    Long id,
    String handle,
    String displayName,
    String description,
    String banner,
    String profile
) {

}
