package com.pellto.youtoy.global.dto.member;

import java.time.LocalDateTime;

public record MemberInfoDto(
    String email,
    String pwd,
    String name,
    LocalDateTime birthDate
) {

}
