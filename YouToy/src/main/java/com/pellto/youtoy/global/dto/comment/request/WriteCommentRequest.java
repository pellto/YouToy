package com.pellto.youtoy.global.dto.comment.request;

public record WriteCommentRequest(
    Long commenterId,
    Long contentsId,
    String contentsType,
    String content
) {

}
