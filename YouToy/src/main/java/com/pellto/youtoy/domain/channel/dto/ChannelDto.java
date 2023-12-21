package com.pellto.youtoy.domain.channel.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ChannelDto(
    Long id,
    String handle,
    String displayName,
    String description,
    String banner,
    String profile,
    Long subscriberCount,
    List<Long> subscribedList,
    LocalDateTime createdAt
) {

}
