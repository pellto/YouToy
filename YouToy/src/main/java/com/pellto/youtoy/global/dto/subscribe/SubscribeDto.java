package com.pellto.youtoy.global.dto.subscribe;

import java.time.LocalDateTime;

public record SubscribeDto(
    Long id,
    Long subscriberId,
    Long subscribedChannelId,
    String subscribedLevel,
    LocalDateTime createdAt
) {

}
