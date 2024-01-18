package com.pellto.youtoy.membership.domain.model;

import com.pellto.youtoy.global.util.General;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;


@Getter
public class Membership {

  private final Long id;
  private final String email;
  private final LocalDateTime startedAt;
  private LocalDateTime expectedExpiredAt;
  private Premium premium;

  @Builder
  public Membership(Long id, String email, LocalDateTime startedAt,
      LocalDateTime expectedExpiredAt, Premium premium) {
    this.id = id;
    this.email = email;
    this.startedAt = startedAt;
    this.expectedExpiredAt = General.setNullInput(expectedExpiredAt, startedAt.plusDays(30));
    this.premium = General.setNullInput(premium, Premium.NORMAL);
  }

  public void changePremium(Premium premium) {
    this.premium = premium;
  }

  public void renew() {
    this.expectedExpiredAt = expectedExpiredAt.plusDays(30);
  }
}
