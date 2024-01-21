package com.pellto.youtoy.membership.domain.port.in;

import com.pellto.youtoy.global.dto.member.MemberDto;

public interface RemoveMembershipUsecase {

  void remove(MemberDto dto);
}
