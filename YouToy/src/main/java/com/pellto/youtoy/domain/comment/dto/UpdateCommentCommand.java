package com.pellto.youtoy.domain.comment.dto;

import javax.validation.constraints.NotNull;

public record UpdateCommentCommand(
    @NotNull
    Long id,
    String content
) {

}
