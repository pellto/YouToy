package com.pellto.youtoy.domain.video.dto;

public record VideoCommentInterestDto(
    Long id,
    Long commentId,
    String userUuid,
    boolean dislike
) {

}
