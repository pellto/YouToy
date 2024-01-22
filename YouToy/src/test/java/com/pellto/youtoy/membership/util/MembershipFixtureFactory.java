package com.pellto.youtoy.membership.util;

import com.pellto.youtoy.global.dto.member.MemberInfoDto;
import com.pellto.youtoy.membership.domain.model.Membership;
import com.pellto.youtoy.membership.domain.model.Premium;
import java.time.LocalDateTime;

public class MembershipFixtureFactory {

  private static final Long ID = 1L;
  private static final String EMAIL = "test@email.com";
  private static final LocalDateTime STARTED_AT = LocalDateTime.now();
  private static final LocalDateTime EXPECTED_EXPIRED_AT = STARTED_AT.plusDays(30);
  private static final Premium PREMIUM = Premium.NORMAL;

  public static Membership create(Long id) {
    return Membership.builder()
        .id(id)
        .email(EMAIL)
        .startedAt(STARTED_AT)
        .expectedExpiredAt(EXPECTED_EXPIRED_AT)
        .premium(PREMIUM)
        .build();
  }

  public static Membership createBeforePublished(MemberInfoDto memberInfoDto,
      LocalDateTime requestedAt) {
    return Membership.builder()
        .email(memberInfoDto.email())
        .startedAt(requestedAt)
        .build();
  }

  public static Membership create() {
    return Membership.builder()
        .id(ID)
        .email(EMAIL)
        .startedAt(STARTED_AT)
        .expectedExpiredAt(EXPECTED_EXPIRED_AT)
        .premium(PREMIUM)
        .build();
  }
}
