package com.pellto.youtoy.domain.video.dto;

import javax.validation.constraints.NotNull;

public record UpdateShortCommand(
        @NotNull(message = "[short] 채널 id는 필수입니다.")
        Long id,
        String title,
        String description
) {
}
