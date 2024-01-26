package com.pellto.youtoy.auth.service;

import com.pellto.youtoy.auth.domain.model.Auth;
import com.pellto.youtoy.auth.domain.port.in.AuthTokenIssueUsecase;
import com.pellto.youtoy.auth.domain.port.out.MemberInfoHandleUsecase;
import com.pellto.youtoy.global.dto.auth.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthTokenIssueUsecase {

  private final MemberInfoHandleUsecase memberInfoHandleUsecase;

  @Override
  public LoginResponse issue(String email, String pwd) {
    var info = memberInfoHandleUsecase.getMemberInfo(email);
    if (!info.pwd().equals(pwd)) {
      throw new IllegalArgumentException("id, pwd 틀림");
    }

    var auth = Auth.builder()
        .infos(info)
        .build();

    return new LoginResponse(auth.createToken());
  }

}
