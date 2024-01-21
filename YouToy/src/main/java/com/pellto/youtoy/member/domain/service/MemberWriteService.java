package com.pellto.youtoy.member.domain.service;

import com.pellto.youtoy.global.dto.member.MemberInfoDto;
import com.pellto.youtoy.global.dto.member.request.MemberSignUpRequest;
import com.pellto.youtoy.member.domain.model.Member;
import com.pellto.youtoy.member.domain.model.MemberInfo;
import com.pellto.youtoy.member.domain.port.in.MemberInfoUsecase;
import com.pellto.youtoy.member.domain.port.in.MemberSignUpUsecase;
import com.pellto.youtoy.member.domain.port.out.LoadMemberPort;
import com.pellto.youtoy.member.domain.port.out.MemberEventPort;
import com.pellto.youtoy.member.domain.port.out.SaveMemberPort;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberWriteService implements MemberInfoUsecase, MemberSignUpUsecase {

  private final LoadMemberPort loadMemberPort;
  private final SaveMemberPort saveMemberPort;
  private final MemberEventPort memberEventPort;

  @Override
  public void changeInfo(MemberInfo memberInfo) {

  }

  @Override
  public void changePwd(String pwd) {

  }

  @Override
  public void changeName(String name) {

  }

  @Override
  public void requestSignUp(MemberSignUpRequest request) {
    if (!request.memberInfoDto().pwd().equals(request.repeatPwd())) {
      throw new IllegalArgumentException();
    }

    memberEventPort.requestedSignUpEvent(
        request.memberInfoDto(),
        LocalDateTime.now()
    );
  }

  @Override
  public Member signUp(Long membershipId, MemberInfoDto memberInfoDto) {
    var member = Member.builder()
        .membershipId(membershipId)
        .memberInfo(MemberInfo.fromDto(memberInfoDto))
        .build();

    member = saveMemberPort.save(member);
    memberEventPort.signedUpEvent(
        member.getId(),
        member.getMemberInfo().getName(),
        member.getUuid().value(),
        member.getMembershipId()
    );
    return member;
  }

}
