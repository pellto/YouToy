package com.pellto.youtoy.domain.post.dto;

public record PostReplyInterestDto(
    Long id,
    Long replyId,
    String userUuid,
    boolean dislike
) {

}
