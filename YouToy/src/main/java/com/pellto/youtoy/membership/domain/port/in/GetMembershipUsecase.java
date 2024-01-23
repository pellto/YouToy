package com.pellto.youtoy.membership.domain.port.in;

import com.pellto.youtoy.global.dto.membership.MembershipDto;

public interface GetMembershipUsecase {

  MembershipDto getMembershipInfo(Long id);
}
