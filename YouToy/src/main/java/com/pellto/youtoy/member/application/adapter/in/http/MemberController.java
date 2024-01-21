package com.pellto.youtoy.member.application.adapter.in.http;

import com.pellto.youtoy.global.dto.member.request.MemberChangeNameRequest;
import com.pellto.youtoy.global.dto.member.request.MemberChangePwdRequest;
import com.pellto.youtoy.global.dto.member.request.MemberSignUpRequest;
import com.pellto.youtoy.member.domain.port.in.MemberDeleteUsecase;
import com.pellto.youtoy.member.domain.port.in.MemberInfoUsecase;
import com.pellto.youtoy.member.domain.port.in.MemberSignUpUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

  private final MemberSignUpUsecase signUpUsecase;
  private final MemberInfoUsecase memberInfoUsecase;
  private final MemberDeleteUsecase memberDeleteUsecase;

  @PostMapping
  public void signUp(@RequestBody MemberSignUpRequest req) {
    signUpUsecase.requestSignUp(req);
  }

  @PatchMapping("/name")
  public void changeName(@RequestBody MemberChangeNameRequest req) {
    memberInfoUsecase.changeName(req);
  }

  @PatchMapping("/pwd")
  public void changePwd(@RequestBody MemberChangePwdRequest req) {
    memberInfoUsecase.changePwd(req);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    memberDeleteUsecase.delete(id);
  }
}
