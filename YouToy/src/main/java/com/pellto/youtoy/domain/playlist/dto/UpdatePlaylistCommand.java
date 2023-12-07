package com.pellto.youtoy.domain.playlist.dto;

import javax.validation.constraints.NotNull;

public record UpdatePlaylistCommand(
    @NotNull
    Long id,
    String title,
    Integer targetRange
) {

}
