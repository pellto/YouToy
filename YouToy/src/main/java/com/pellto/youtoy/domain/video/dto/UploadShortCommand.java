package com.pellto.youtoy.domain.video.dto;

import javax.validation.constraints.NotNull;

public record UploadShortCommand(
        // TODO: content save location?
        @NotNull(message = "[short] 채널 id는 필수입니다.")
        Long channelId,
        String title,
        String description
) {
}
