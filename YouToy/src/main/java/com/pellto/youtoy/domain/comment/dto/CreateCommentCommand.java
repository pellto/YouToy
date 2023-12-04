package com.pellto.youtoy.domain.comment.dto;

import javax.validation.constraints.NotNull;

public record CreateCommentCommand(
        @NotNull
        Long videoId,
        @NotNull
        Long userId,
        @NotNull
        Boolean video,
        @NotNull
        String content,
        Long repliedCommentId
) {
}
