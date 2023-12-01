package com.pellto.youtoy.domain.playlist.dto;

public record PlaylistVideoDto(
        Long id,
        Long playlistId,
        Long videoId,
        Integer videoType
) {
}
