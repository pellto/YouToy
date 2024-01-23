package com.pellto.youtoy.member.domain.service;

import com.pellto.youtoy.global.dto.member.MemberInfoDto;
import com.pellto.youtoy.global.dto.member.request.MemberChangeNameRequest;
import com.pellto.youtoy.global.dto.member.request.MemberChangePwdRequest;
import com.pellto.youtoy.global.dto.member.request.MemberSignUpRequest;
import com.pellto.youtoy.member.domain.model.Member;
import com.pellto.youtoy.member.domain.model.MemberInfo;
import com.pellto.youtoy.member.domain.port.in.ChangeMemberInfoUsecase;
import com.pellto.youtoy.member.domain.port.in.MemberDeleteUsecase;
import com.pellto.youtoy.member.domain.port.in.MemberSignUpUsecase;
import com.pellto.youtoy.member.domain.port.out.LoadMemberPort;
import com.pellto.youtoy.member.domain.port.out.MemberEventPort;
import com.pellto.youtoy.member.domain.port.out.SaveMemberPort;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangeMemberWriteService implements ChangeMemberInfoUsecase, MemberSignUpUsecase,
    MemberDeleteUsecase {

  private final LoadMemberPort loadMemberPort;
  private final SaveMemberPort saveMemberPort;
  private final MemberEventPort memberEventPort;

  @Override
  public void changeInfo(MemberInfo memberInfo) {

  }

  @Override
  public void changePwd(MemberChangePwdRequest req) {
    checkValidPwd(req.pwd(), req.repeatPwd());

    var member = loadMemberPort.load(req.id());

    MemberInfoDto before = member.getMemberInfoDto();
    member.changeMemberPwd(req.pwd());
    MemberInfoDto after = member.getMemberInfoDto();

    saveMemberPort.update(member);

    memberEventPort.memberInfoChangedEvent(before, after);
  }

  @Override
  public void changeName(MemberChangeNameRequest req) {
    var member = loadMemberPort.load(req.id());

    MemberInfoDto before = member.getMemberInfoDto();
    member.changeMemberName(req.name());
    MemberInfoDto after = member.getMemberInfoDto();

    saveMemberPort.update(member);

    memberEventPort.memberInfoChangedEvent(before, after);
  }

  @Override
  public void delete(Long id) {
    // TODO: check valid auth
    var member = loadMemberPort.load(id);
    saveMemberPort.delete(member);

    memberEventPort.memberDeletedEvent(member.toDto());
  }

  @Override
  public void requestSignUp(MemberSignUpRequest request) {
    checkValidPwd(request.memberInfoDto().pwd(), request.repeatPwd());

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

  private void checkValidPwd(String pwd, String repeatPwd) {
    if (!pwd.equals(repeatPwd)) {
      throw new IllegalArgumentException();
    }
  }
}
