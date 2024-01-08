package com.pellto.youtoy.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserSignInResponse(
    @Schema(description = "회원 이름", example = "pellto")
    String name,
    @Schema(description = "회원 유형", example = "USER")
    MemberType type,
    String token
) {

}
