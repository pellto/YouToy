package com.pellto.youtoy.auth.domain.port.out;

import com.pellto.youtoy.global.dto.member.response.GetMemberInfoResponse;

public interface MemberInfoHandlePort {

  GetMemberInfoResponse getMemberInfo(String email);
}
