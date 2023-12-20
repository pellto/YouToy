package com.pellto.youtoy.domain.user.dto;

import com.pellto.youtoy.domain.user.domain.PremiumLevel;
import java.time.LocalDateTime;

public record UserDto(
    Long id,
    String email,
    String name,
    PremiumLevel premiumLevel,
    LocalDateTime birthDate,
    LocalDateTime createdAt
) {

}
