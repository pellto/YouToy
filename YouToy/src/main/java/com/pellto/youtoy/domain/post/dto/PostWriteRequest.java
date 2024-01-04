package com.pellto.youtoy.domain.post.dto;

public record PostWriteRequest(
    Long channelId,
    String content
) {

}
