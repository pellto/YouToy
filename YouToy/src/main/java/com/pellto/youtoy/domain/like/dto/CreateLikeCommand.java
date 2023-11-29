package com.pellto.youtoy.domain.like.dto;

import javax.validation.constraints.NotNull;

public record CreateLikeCommand(
        @NotNull
        Long userId,
        Long videoId,
        Integer videoType,
        Long commentId
) {
}
