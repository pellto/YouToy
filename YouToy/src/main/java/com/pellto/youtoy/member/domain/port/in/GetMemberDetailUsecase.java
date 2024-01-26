package com.pellto.youtoy.member.domain.port.in;

import com.pellto.youtoy.global.dto.member.MemberInfoDto;
import com.pellto.youtoy.global.dto.member.response.GetMemberResponse;

public interface GetMemberDetailUsecase {

  GetMemberResponse getMemberDetail(Long id);

  MemberInfoDto getMemberInfoByEmail(String email);
}
