package com.pellto.youtoy.domain.view.dto;

import java.time.LocalDateTime;

public record ViewHistoryDto(
    Long id,
    Long userId,
    Long videoId,
    Integer videoType,
    Long lastViewAt,
    LocalDateTime createdAt
) {

}
