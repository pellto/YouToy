package com.pellto.youtoy.global.dto.video;

import lombok.Builder;

@Builder
public record VideoDetailInfoDto(
    String title,
    String description,
    String hashTags
) {

}
