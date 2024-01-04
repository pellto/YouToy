package com.pellto.youtoy.domain.video.dto;

public record VideoReplyInterestDto(
    Long id,
    Long replyId,
    String userUuid,
    boolean dislike
) {

}
