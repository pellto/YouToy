package com.pellto.youtoy.domain.channel.dto;

import com.pellto.youtoy.domain.channel.domain.AuthLevel;

public record AdminDto(
    Long id,
    Long channelId,
    String adminUuid,
    AuthLevel level
) {

}
