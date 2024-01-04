package com.pellto.youtoy.domain.post.dto;

public record PostCommentInterestDto(
    Long id,
    Long commentId,
    String userUuid,
    boolean dislike
) {

}
