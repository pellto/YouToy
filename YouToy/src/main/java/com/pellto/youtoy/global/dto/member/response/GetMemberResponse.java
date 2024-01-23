package com.pellto.youtoy.global.dto.member.response;

import com.pellto.youtoy.global.dto.member.MemberInfoDto;
import com.pellto.youtoy.global.dto.membership.MembershipDto;
import java.time.LocalDateTime;

public record GetMemberResponse(
    Long id,
    String memberUuid,
    LocalDateTime createdAt,
    MemberInfoDto memberInfo,
    MembershipDto membership
) {

}
