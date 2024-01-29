package com.pellto.youtoy.global.dto.interest;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record InterestDto(
    Long id,
    Long memberId,
    Long contentsId,
    String contentsType,
    LocalDateTime createdAt,
    boolean isLike
) {

}
