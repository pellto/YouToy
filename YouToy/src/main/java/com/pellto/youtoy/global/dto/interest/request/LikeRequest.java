package com.pellto.youtoy.global.dto.interest.request;

public record LikeRequest(
    Long memberId,
    Long contentsId,
    // TODO: Fix to ContentsType
    String contentsType
) {

}
