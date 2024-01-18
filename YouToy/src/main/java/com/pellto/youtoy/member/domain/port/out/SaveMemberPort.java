package com.pellto.youtoy.member.domain.port.out;

import com.pellto.youtoy.member.domain.model.Member;

public interface SaveMemberPort {

  Member save(Member member);
}
