package com.pellto.youtoy.member.domain.port.in;

import com.pellto.youtoy.member.domain.model.MemberInfo;

public interface MemberInfoUsecase {

  void changeInfo(MemberInfo memberInfo);

  void changePwd(String pwd);

  void changeName(String name);
}
