package com.pellto.youtoy.domain.video.dto;

import javax.validation.constraints.NotNull;

public record UpdateVideoCommand(
        @NotNull
        Long id,
        String title,
        String description
) {
}
