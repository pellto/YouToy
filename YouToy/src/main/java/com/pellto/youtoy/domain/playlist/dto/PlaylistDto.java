package com.pellto.youtoy.domain.playlist.dto;

public record PlaylistDto(
    Long id,
    Long channelId,
    String title,
    Integer targetRange
) {

}
