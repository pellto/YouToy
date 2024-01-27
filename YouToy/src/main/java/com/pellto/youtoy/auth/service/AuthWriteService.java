package com.pellto.youtoy.auth.service;

import com.pellto.youtoy.auth.domain.model.Auth;
import com.pellto.youtoy.auth.domain.model.ChannelRoles;
import com.pellto.youtoy.auth.domain.model.MemberRoles;
import com.pellto.youtoy.auth.domain.port.in.AuthTokenIssueUsecase;
import com.pellto.youtoy.auth.domain.port.out.ChannelManagementHandlePort;
import com.pellto.youtoy.auth.domain.port.out.MemberInfoHandlePort;
import com.pellto.youtoy.auth.domain.port.out.TokenServicePort;
import com.pellto.youtoy.global.dto.auth.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthWriteService implements AuthTokenIssueUsecase {

  private final MemberInfoHandlePort memberInfoHandlePort;
  private final ChannelManagementHandlePort channelManageMentHandlePort;
  private final TokenServicePort tokenServicePort;

  @Override
  public LoginResponse issue(String email, String pwd) {
    var memberInfoResponse = memberInfoHandlePort.getMemberInfo(email);
    if (!memberInfoResponse.pwd().equals(pwd)) {
      throw new IllegalArgumentException("id, pwd 틀림");
    }
    var memberRoles = MemberRoles.fromMemberInfo(memberInfoResponse.toMemberInfoDto());

    var channelManagement = channelManageMentHandlePort.getChannelManagement(
        memberInfoResponse.memberId());
    var channelRoles = ChannelRoles.fromManagement(channelManagement);

    var auth = Auth.builder()
        .email(email)
        .channelRoles(channelRoles)
        .memberRoles(memberRoles)
        .build();

    var token = tokenServicePort.generateToken(auth.toDto());

    return new LoginResponse(token);
  }
}
