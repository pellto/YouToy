package com.pellto.youtoy.domain.post.dto;

public record PostInterestDto(
    Long id,
    Long postId,
    String userUuid,
    boolean dislike
) {

}
