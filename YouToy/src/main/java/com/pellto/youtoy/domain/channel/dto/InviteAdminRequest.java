package com.pellto.youtoy.domain.channel.dto;

import com.pellto.youtoy.domain.channel.domain.AuthLevel;

public record InviteAdminRequest(
    String userUuid,
    Long targetChannelId,
    AuthLevel authLevel
) {

}
