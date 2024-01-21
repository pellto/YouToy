package com.pellto.youtoy.member.domain.port.out;

import com.pellto.youtoy.global.dto.member.MemberDto;
import com.pellto.youtoy.global.dto.member.MemberInfoDto;
import java.time.LocalDateTime;

public interface MemberEventPort {

  void memberDeletedEvent(MemberDto dto);

  void requestedSignUpEvent(MemberInfoDto memberInfoDto, LocalDateTime requiredAt);

  void signedUpEvent(Long memberId, String memberName, String memberUuid, Long membershipId);

  void memberInfoChangedEvent(MemberInfoDto before, MemberInfoDto after);
}
