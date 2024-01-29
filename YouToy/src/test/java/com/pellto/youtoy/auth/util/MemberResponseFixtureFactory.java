package com.pellto.youtoy.auth.util;

import com.pellto.youtoy.global.dto.member.response.GetMemberInfoResponse;
import java.time.LocalDateTime;

public class MemberResponseFixtureFactory {

  private static final Long MEMBER_ID = 1L;
  private static final String EMAIL = "test@email.com";
  private static final String PWD = "testPwd";
  private static final String NAME = "testName";
  private static final LocalDateTime BIRTH_DATE = LocalDateTime.now();

  public static GetMemberInfoResponse createGetMemberInfoResponse() {
    return new GetMemberInfoResponse(MEMBER_ID, EMAIL, PWD, NAME, BIRTH_DATE);
  }
}
