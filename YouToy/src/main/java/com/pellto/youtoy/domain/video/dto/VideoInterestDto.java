package com.pellto.youtoy.domain.video.dto;

public record VideoInterestDto(
    Long id,
    Long contentsId,
    String userUuid,
    boolean dislike
) {

}
