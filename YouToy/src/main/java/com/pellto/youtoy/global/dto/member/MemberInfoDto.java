package com.pellto.youtoy.global.dto.member;

import java.time.LocalDateTime;

public record MemberInfoDto(
    Long memberId,
    String email,
    String pwd,
    String name,
    LocalDateTime birthDate
) {

}
