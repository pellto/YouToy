package com.pellto.youtoy.membership.domain.port.out;

import com.pellto.youtoy.global.dto.member.MemberInfoDto;

public interface MembershipEventPort {

  void publishedInitMembership(Long membershipId, MemberInfoDto memberInfoDto);
}
