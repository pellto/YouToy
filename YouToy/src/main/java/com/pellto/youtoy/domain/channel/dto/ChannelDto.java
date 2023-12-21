package com.pellto.youtoy.domain.channel.dto;

import java.time.LocalDateTime;

public record ChannelDto(
    Long id,
    String handle,
    String displayName,
    String description,
    String banner,
    String profile,
    LocalDateTime createdAt
) {

}
