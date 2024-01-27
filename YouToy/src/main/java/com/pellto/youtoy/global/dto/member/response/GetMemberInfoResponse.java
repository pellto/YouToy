package com.pellto.youtoy.global.dto.member.response;

import com.pellto.youtoy.global.dto.member.MemberInfoDto;
import java.time.LocalDateTime;

public record GetMemberInfoResponse(
    Long memberId,
    String email,
    String pwd,
    String name,
    LocalDateTime birthDate
) {

  public MemberInfoDto toMemberInfoDto() {
    return new MemberInfoDto(email, pwd, name, birthDate);
  }
  
}
