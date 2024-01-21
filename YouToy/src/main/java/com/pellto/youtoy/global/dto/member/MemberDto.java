package com.pellto.youtoy.global.dto.member;

import java.time.LocalDateTime;

public record MemberDto(
    Long id,
    String memberUuid,
    LocalDateTime createdAt,
    MemberInfoDto memberInfo,
    Long membershipId
) {

}
