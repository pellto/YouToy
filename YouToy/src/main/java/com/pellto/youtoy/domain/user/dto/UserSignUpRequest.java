package com.pellto.youtoy.domain.user.dto;

import java.time.LocalDateTime;

public record UserSignUpRequest(
    String email,
    LocalDateTime birthDate,
    String pwd,
    String repeatPwd,
    String name
) {

}
