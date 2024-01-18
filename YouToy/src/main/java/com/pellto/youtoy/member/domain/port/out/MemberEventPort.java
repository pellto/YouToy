package com.pellto.youtoy.member.domain.port.out;

import com.pellto.youtoy.global.dto.member.MemberInfoDto;
import java.time.LocalDateTime;

public interface MemberEventPort {

  void requestedSignUpEvent(MemberInfoDto memberInfoDto, LocalDateTime requiredAt);

  void signedUpEvent(Long memberId, String memberUuid, Long membershipId);

}
