package com.pellto.youtoy.domain.user.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record UpdateUserInfoCommand(
        @NotNull(message = "유저의 Id는 필수 값 입니다.")
        Long id,
        @Email(message = "유효한 Email 형식이 아닙니다.")
        String email,
        String pwd,
        String repeatPwd,
        String name,
        LocalDate birthDate
) {
}
