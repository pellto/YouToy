package com.pellto.youtoy.membership.application.adapter.out.persistence;

import com.pellto.youtoy.membership.domain.model.Membership;
import com.pellto.youtoy.membership.domain.model.Premium;
import org.springframework.stereotype.Component;

@Component
public class MembershipMapper {

  public Membership toDomain(MembershipEntity entity) {
    var premium = this.fromString(entity.getPremium());
    return Membership.builder()
        .id(entity.getId())
        .email(entity.getEmail())
        .startedAt(entity.getStartedAt())
        .expectedExpiredAt(entity.getExpectedExpiredAt())
        .premium(premium)
        .build();
  }

  public MembershipEntity toEntity(Membership membership) {
    return MembershipEntity.builder()
        .id(membership.getId())
        .email(membership.getEmail())
        .startedAt(membership.getStartedAt())
        .expectedExpiredAt(membership.getExpectedExpiredAt())
        .premium(membership.getPremium().getValue())
        .build();
  }

  private Premium fromString(String premium) {
    switch (premium) {
      case ("PREMIUM") -> {
        return Premium.PREMIUM;
      }
      case ("NORMAL") -> {
        return Premium.NORMAL;
      }
      default -> {
        throw new IllegalArgumentException();
      }
    }
  }
}
