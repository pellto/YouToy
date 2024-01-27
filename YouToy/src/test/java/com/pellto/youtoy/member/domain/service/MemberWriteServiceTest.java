package com.pellto.youtoy.member.domain.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.member.domain.model.Member;
import com.pellto.youtoy.member.domain.port.out.LoadMemberPort;
import com.pellto.youtoy.member.domain.port.out.MemberEventPort;
import com.pellto.youtoy.member.domain.port.out.SaveMemberPort;
import com.pellto.youtoy.member.util.MemberFixtureFactory;
import com.pellto.youtoy.member.util.MemberInfoFixtureFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@Tag("service")
class MemberWriteServiceTest {

  private static final String SERVICE_NAME = "MemberWriteService";

  @InjectMocks
  private ChangeMemberWriteService memberWriteService;
  @Mock
  private LoadMemberPort loadMemberPort;
  @Mock
  private SaveMemberPort saveMemberPort;
  @Mock
  private MemberEventPort memberEventPort;

  @DisplayName("[" + SERVICE_NAME + "/changePwd] changePwd Invalid pwd 테스트")
  @Test
  void changePwdFailedWithInvalidPwdTest() {
    var request = MemberFixtureFactory.createChangeWrongPwdRequest();

    Assertions.assertThatThrownBy(() -> memberWriteService.changePwd(request)).isInstanceOf(
        IllegalArgumentException.class);

    then(loadMemberPort).should(times(0)).load(any());
    then(saveMemberPort).should(times(0)).update(any());
    then(memberEventPort).should(times(0)).memberInfoChangedEvent(any(), any());
  }

  @DisplayName("[" + SERVICE_NAME + "/changePwd] changePwd 성공 테스트")
  @Test
  void changePwdSuccessTest() {
    var request = MemberFixtureFactory.createChangePwdRequest();
    var member = MemberFixtureFactory.create(request.id());

    given(loadMemberPort.load(request.id())).willReturn(member);

    memberWriteService.changePwd(request);

    then(loadMemberPort).should(times(1)).load(request.id());
    then(saveMemberPort).should(times(1)).update(member);
    then(memberEventPort).should(times(1)).memberInfoChangedEvent(any(), any());
  }

  @DisplayName("[" + SERVICE_NAME + "/changeName] changeName 성공 테스트")
  @Test
  void changeNameSuccessTest() {
    var request = MemberFixtureFactory.createChangeNameRequest();
    var member = MemberFixtureFactory.create(request.id());

    given(loadMemberPort.load(request.id())).willReturn(member);

    memberWriteService.changeName(request);

    then(loadMemberPort).should(times(1)).load(request.id());
    then(saveMemberPort).should(times(1)).update(member);
    then(memberEventPort).should(times(1)).memberInfoChangedEvent(any(), any());
  }

  @DisplayName("[" + SERVICE_NAME + "/delete] delete 성공 테스트")
  @Test
  void deleteSuccessTest() {
    var member = MemberFixtureFactory.create();

    given(loadMemberPort.load(member.getId())).willReturn(member);

    memberWriteService.delete(member.getId());

    then(loadMemberPort).should(times(1)).load(member.getId());
    then(saveMemberPort).should(times(1)).delete(member);
    then(memberEventPort).should(times(1)).memberDeletedEvent(member.toDto());
  }

  @DisplayName("[" + SERVICE_NAME + "/requestSignUp] requestSignUp 성공 테스트")
  @Test
  void requestSignUpSuccessTest() {
    var request = MemberFixtureFactory.createSignUpRequest();

    memberWriteService.requestSignUp(request);

    then(memberEventPort).should(times(1)).requestedSignUpEvent(any(), any());
  }

  @DisplayName("[" + SERVICE_NAME + "/requestSignUp] requestSignUp Invalid pwd 실패 테스트")
  @Test
  void requestSignUpFailedWithInvalidPwdTest() {
    var request = MemberFixtureFactory.createSignUpWrongPwdRequest();

    Assertions.assertThatThrownBy(() -> memberWriteService.requestSignUp(request)).isInstanceOf(
        IllegalArgumentException.class);

    then(memberEventPort).should(times(0)).requestedSignUpEvent(any(), any());
  }

  @DisplayName("[" + SERVICE_NAME + "/signUp] signUp 성공 테스트")
  @Test
  void signUpSuccessTest() {
    var memberInfo = MemberInfoFixtureFactory.create();
    var member = MemberFixtureFactory.create(memberInfo);

    given(saveMemberPort.save(any())).willReturn(member);

    var signedUpMember = memberWriteService.signUp(member.getMembershipId(),
        memberInfo.toDto(member.getId()));

    then(saveMemberPort).should(times(1)).save(any());
    then(memberEventPort).should(times(1)).signedUpEvent(any(), any(), any(), any());
    Assertions.assertThat(signedUpMember).isNotNull();
    Assertions.assertThat(signedUpMember).usingRecursiveComparison().isEqualTo(member);
    Assertions.assertThat(signedUpMember.getClass()).isEqualTo(Member.class);
  }
}