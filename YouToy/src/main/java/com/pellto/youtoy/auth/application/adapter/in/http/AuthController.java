package com.pellto.youtoy.auth.application.adapter.in.http;

import com.pellto.youtoy.auth.domain.port.in.AuthTokenIssueUsecase;
import com.pellto.youtoy.global.dto.auth.request.LoginRequest;
import com.pellto.youtoy.global.dto.auth.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthTokenIssueUsecase authTokenIssueUsecase;

  @PostMapping
  public LoginResponse login(LoginRequest request) {
    return authTokenIssueUsecase.issue(request.email(), request.pwd());
  }
}
