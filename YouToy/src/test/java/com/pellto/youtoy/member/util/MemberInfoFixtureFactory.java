package com.pellto.youtoy.member.util;

import com.pellto.youtoy.member.domain.model.MemberInfo;
import java.time.LocalDateTime;

public class MemberInfoFixtureFactory {

  private static final String EMAIL = "test@email.com";
  private static final String PWD = "pwd";
  private static final String NAME = "name";
  private static final LocalDateTime BIRTH_DATE = LocalDateTime.now();

  public static MemberInfo create() {
    return MemberInfo.builder()
        .email(EMAIL)
        .pwd(PWD)
        .name(NAME)
        .birthDate(BIRTH_DATE)
        .build();
  }
}
