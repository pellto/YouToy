package com.pellto.youtoy.domain.channel.dto;

import com.pellto.youtoy.domain.channel.domain.ChannelInfo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreateChannelRequest(
    @NotNull(message = "소유자 uuid는 필수 입니다.")
    @Pattern(
        regexp = "^[a-zA-Z0-9]{10}-[a-zA-Z0-9]{10}-[a-zA-Z0-9]{20}$",
        message = "올바른 uuid 형식이 아닙니다."
    )
    String ownerUuid,
    ChannelInfo channelInfo
) {

}
