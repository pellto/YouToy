package com.pellto.youtoy.domain.user.dto;


import java.time.LocalDate;

public record RegisterUserCommand(
    String email,
    String pwd,
    String repeatPwd,
    String name,
    LocalDate birthDate
) {

}
