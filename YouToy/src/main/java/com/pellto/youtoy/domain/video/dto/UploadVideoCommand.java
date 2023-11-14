package com.pellto.youtoy.domain.video.dto;

import javax.validation.constraints.NotNull;

public record UploadVideoCommand(
        // TODO: content save location?
        @NotNull(message = "[video] 채널 id는 필수입니다.")
        Long channelId,
        String title,
        String description
) {
}
