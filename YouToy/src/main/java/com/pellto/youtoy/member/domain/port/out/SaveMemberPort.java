package com.pellto.youtoy.member.domain.port.out;

import com.pellto.youtoy.member.domain.model.Member;

public interface SaveMemberPort {

  void delete(Member member);

  Member save(Member member);

  void update(Member member);
}
