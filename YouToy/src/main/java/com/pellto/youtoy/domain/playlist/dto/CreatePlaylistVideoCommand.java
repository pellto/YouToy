package com.pellto.youtoy.domain.playlist.dto;

public record CreatePlaylistVideoCommand(
        Long playlistId,
        Long videoId,
        Integer videoType
) {
}
