package com.pellto.youtoy.domain.community.dto;

public record PostInterestDto(
    Long postId,
    String userUuid,
    boolean dislike
) {

}
