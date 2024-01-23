package com.pellto.youtoy.member.domain.port.in;

import com.pellto.youtoy.global.dto.member.request.MemberChangeNameRequest;
import com.pellto.youtoy.global.dto.member.request.MemberChangePwdRequest;
import com.pellto.youtoy.member.domain.model.MemberInfo;

public interface ChangeMemberInfoUsecase {

  void changeInfo(MemberInfo memberInfo);

  void changePwd(MemberChangePwdRequest req);

  void changeName(MemberChangeNameRequest req);
}
