package com.pellto.youtoy.membership.domain.port.out;

import com.pellto.youtoy.global.dto.member.MemberInfoDto;
import com.pellto.youtoy.global.dto.membership.MembershipDto;

public interface MembershipEventPort {

  void publishedInitMembership(Long membershipId, MemberInfoDto memberInfoDto);

  void membershipRemovedEvent(MembershipDto dto);
}
