package com.pellto.youtoy.domain.playlist.dto;

import javax.validation.constraints.NotNull;

public record CreatePlaylistCommand(
    @NotNull
    Long channelId,
    String title,
    Integer targetRange
) {

}
