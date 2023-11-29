package com.pellto.youtoy.domain.like.dto;

public record LikeDto(
        Long id,
        Long videoId,
        Integer videoType,
        Long commentId,
        Long userId
) {
}
