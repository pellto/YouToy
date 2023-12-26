package com.pellto.youtoy.domain.community.dto;

public record CommunityCommentInterestDto(
    Long commentId,
    String userUuid,
    boolean dislike
) {

}
