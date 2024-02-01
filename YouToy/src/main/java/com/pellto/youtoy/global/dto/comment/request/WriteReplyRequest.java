package com.pellto.youtoy.global.dto.comment.request;

public record WriteReplyRequest(
    Long replierId,
    Long parentCommentId,
    String content
) {

}
