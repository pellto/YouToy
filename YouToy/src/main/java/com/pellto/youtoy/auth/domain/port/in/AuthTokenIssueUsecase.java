package com.pellto.youtoy.auth.domain.port.in;

import com.pellto.youtoy.global.dto.auth.response.LoginResponse;

public interface AuthTokenIssueUsecase {

  LoginResponse issue(String email, String pwd);
}
