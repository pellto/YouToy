package com.pellto.youtoy.domain.user.dto;


public record LoginUserCommand(
        String email,
        String pwd
) {
}
