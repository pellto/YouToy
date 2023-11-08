package com.pellto.youtoy.domain.user.dto;


public record RegisterUserCommand(
        String email,
        String pwd,
        String repeatPwd
) {
}
