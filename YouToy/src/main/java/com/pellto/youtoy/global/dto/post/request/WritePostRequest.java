package com.pellto.youtoy.global.dto.post.request;

public record WritePostRequest(
    Long channelId,
    String title,
    String content
) {

}
