package com.pellto.youtoy.domain.view.dto;

public record CreateViewHistoryCommand(
    Long userId,
    Long videoId,
    Integer videoType,
    Long lastViewAt
) {

}
