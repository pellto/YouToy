package com.pellto.youtoy.member.domain.port.in;

import com.pellto.youtoy.global.dto.member.response.GetMemberInfoResponse;
import com.pellto.youtoy.global.dto.member.response.GetMemberResponse;

public interface GetMemberDetailUsecase {

  GetMemberResponse getMemberDetail(Long id);

  GetMemberInfoResponse getMemberInfoByEmail(String email);
}
