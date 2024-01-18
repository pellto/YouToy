package com.pellto.youtoy.member.domain.port.out;

import com.pellto.youtoy.member.domain.model.Member;

public interface LoadMemberPort {

  Member load(Long id);
}
