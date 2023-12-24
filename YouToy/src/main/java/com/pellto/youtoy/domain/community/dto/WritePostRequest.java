package com.pellto.youtoy.domain.community.dto;

public record WritePostRequest(
    Long channelId,
    String content
) {

}
