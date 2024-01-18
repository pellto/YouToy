package com.pellto.youtoy.membership.domain.port.in;

import com.pellto.youtoy.global.dto.member.MemberInfoDto;
import java.time.LocalDateTime;

public interface PublishInitMembershipUsecase {

  void publish(MemberInfoDto memberInfoDto, LocalDateTime requiredAt);
}
