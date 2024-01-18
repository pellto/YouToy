package com.pellto.youtoy.member.domain.port.in;

import com.pellto.youtoy.global.dto.member.MemberInfoDto;
import com.pellto.youtoy.global.dto.member.request.MemberSignUpRequest;
import com.pellto.youtoy.member.domain.model.Member;

public interface MemberSignUpUsecase {

  void RequestSignUp(MemberSignUpRequest request);

  Member signUp(Long membershipId, MemberInfoDto memberInfoDto);

}
