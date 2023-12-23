package com.pellto.youtoy.domain.channel.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateSubscribeRelRequest(
    @Positive(message = "구독자 id 는 양수여야 합니다.")
    @NotNull(message = "구독자 id는 필수 입니다.")
    Long subscriberId,
    @Positive(message = "구독할 채널 id 는 양수여야 합니다.")
    @NotNull(message = "구독할 채널 id는 필수 입니다.")
    Long subscribedId
) {

}
