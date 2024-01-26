package com.pellto.youtoy.global.dto.auth.request;

public record LoginRequest(
    String email,
    String pwd
) {

}
