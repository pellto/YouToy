package com.pellto.youtoy.member.application.adapter.in.http;

import com.pellto.youtoy.global.dto.member.request.MemberSignUpRequest;
import com.pellto.youtoy.member.domain.port.in.MemberSignUpUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

  private final MemberSignUpUsecase signUpUsecase;

  @PostMapping
  public void signUp(@RequestBody MemberSignUpRequest req) {
    signUpUsecase.RequestSignUp(req);
  }
}
