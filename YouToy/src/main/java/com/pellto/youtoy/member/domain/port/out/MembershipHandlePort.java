package com.pellto.youtoy.member.domain.port.out;

import com.pellto.youtoy.global.dto.membership.MembershipDto;

public interface MembershipHandlePort {

  MembershipDto getMembershipInfo(Long id);
}
