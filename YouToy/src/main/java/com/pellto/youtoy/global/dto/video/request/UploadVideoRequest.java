package com.pellto.youtoy.global.dto.video.request;

public record UploadVideoRequest(
    Long channelId,
    String title,
    String description,
    String hashTags
    // TODO: below
    // file, thumbnail ..
) {

}
