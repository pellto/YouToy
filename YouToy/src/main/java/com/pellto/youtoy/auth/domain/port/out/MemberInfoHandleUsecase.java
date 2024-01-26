package com.pellto.youtoy.auth.domain.port.out;

import com.pellto.youtoy.global.dto.member.MemberInfoDto;

public interface MemberInfoHandleUsecase {

  MemberInfoDto getMemberInfo(String email);
}
