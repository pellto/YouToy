package com.pellto.youtoy.domain.channel.dto;

import com.pellto.youtoy.domain.channel.domain.ChannelInfo;
import java.time.LocalDateTime;
import java.util.List;

public record ChannelDto(
    Long id,
    ChannelInfo channelInfo,
    Long subscriberCount,
    List<Long> subscribedList,
    LocalDateTime createdAt
) {

}
