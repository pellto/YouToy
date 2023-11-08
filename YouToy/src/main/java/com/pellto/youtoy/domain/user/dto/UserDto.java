package com.pellto.youtoy.domain.user.dto;

public record UserDto (
        Long id,
        String email,
        String pwd
) {
}
