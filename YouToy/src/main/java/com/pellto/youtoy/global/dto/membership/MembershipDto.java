package com.pellto.youtoy.global.dto.membership;

import java.time.LocalDateTime;

public record MembershipDto(
    Long id,
    String email,
    LocalDateTime startedAt,
    LocalDateTime expectedExpiredAt,
    String premium
) {

}
