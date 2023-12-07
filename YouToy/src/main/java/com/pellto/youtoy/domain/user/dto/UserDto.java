package com.pellto.youtoy.domain.user.dto;

import java.time.LocalDate;

public record UserDto(
    Long id,
    String email,
    String pwd,
    String name,
    LocalDate birthDate
) {

}
