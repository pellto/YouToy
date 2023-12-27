package com.pellto.youtoy.domain.community.dto;

public record CommunityReplyCommentInterestDto(
    Long replyId,
    String userUuid,
    boolean dislike
) {

}
