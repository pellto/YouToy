package com.pellto.youtoy.auth.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.auth.domain.port.out.ChannelManagementHandlePort;
import com.pellto.youtoy.auth.domain.port.out.MemberInfoHandlePort;
import com.pellto.youtoy.auth.domain.port.out.TokenServicePort;
import com.pellto.youtoy.auth.util.ChannelManagementDtoFixtureFactory;
import com.pellto.youtoy.auth.util.MemberResponseFixtureFactory;
import com.pellto.youtoy.global.dto.auth.response.LoginResponse;
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
class AuthWriteServiceTest {

  private static final String SERVICE_NAME = "AuthWriteService";

  @InjectMocks
  private AuthWriteService authWriteService;
  @Mock
  private MemberInfoHandlePort memberInfoHandlePort;
  @Mock
  private ChannelManagementHandlePort channelManageMentHandlePort;
  @Mock
  private TokenServicePort tokenServicePort;

  @DisplayName("[" + SERVICE_NAME + "/issue] issue 성공 테스트")
  @Test
  void issueSuccessTest() {
    var memberInfoResponse = MemberResponseFixtureFactory.createGetMemberInfoResponse();
    var channelManagement = ChannelManagementDtoFixtureFactory.createList();
    var testToken = "testToken";

    given(memberInfoHandlePort.getMemberInfo(any())).willReturn(memberInfoResponse);
    given(channelManageMentHandlePort.getChannelManagement(any())).willReturn(channelManagement);
    given(tokenServicePort.generateToken(any())).willReturn(testToken);

    var loggedInResponse = authWriteService.issue(memberInfoResponse.email(),
        memberInfoResponse.pwd());

    Assertions.assertThat(loggedInResponse).isNotNull();
    Assertions.assertThat(loggedInResponse.getClass()).isEqualTo(LoginResponse.class);
    Assertions.assertThat(loggedInResponse.token()).isEqualTo(testToken);
    then(memberInfoHandlePort).should(times(1)).getMemberInfo(any());
    then(channelManageMentHandlePort).should(times(1)).getChannelManagement(any());
    then(tokenServicePort).should(times(1)).generateToken(any());
  }

  @DisplayName("[" + SERVICE_NAME + "/issue] issue 실패 테스트")
  @Test
  void issueFailWithWrongPwdTest() {
    var memberInfoResponse = MemberResponseFixtureFactory.createGetMemberInfoResponse();

    given(memberInfoHandlePort.getMemberInfo(any())).willReturn(memberInfoResponse);

    Assertions.assertThatThrownBy(() -> authWriteService.issue(memberInfoResponse.email(),
        "wrongPwd")).isInstanceOf(IllegalArgumentException.class).hasMessage("id, pwd 틀림");

    then(memberInfoHandlePort).should(times(1)).getMemberInfo(any());
    then(channelManageMentHandlePort).should(times(0)).getChannelManagement(any());
    then(tokenServicePort).should(times(0)).generateToken(any());
  }
}