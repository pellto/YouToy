package com.pellto.youtoy.domain.video.dto;

public record VideoUploadRequest(
    Long channelId,
    String title,
    String description
) {

}
