package com.pellto.youtoy.global.dto.channel.response;

import lombok.Builder;

@Builder
public record GetCommenterChannelInfoResponse(
    Long commenterId,
    String commenterHandle,
    String displayName,
    String profilePath
) {

}
