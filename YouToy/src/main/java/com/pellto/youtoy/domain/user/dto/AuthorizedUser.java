package com.pellto.youtoy.domain.user.dto;

import com.pellto.youtoy.domain.user.domain.PremiumLevel;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class AuthorizedUser {

  private final String email;
  private final String name;
  private final String userUuid;
  private final List<MemberType> memberType;
  private final PremiumLevel premiumLevel;

  public AuthorizedUser(String email, String name, String userUuid, List<MemberType> memberType,
      PremiumLevel premiumLevel) {
    this.email = email;
    this.name = name;
    this.userUuid = userUuid;
    this.memberType = memberType;
    this.premiumLevel = premiumLevel;
  }

  public AuthorizedUser(List<String> strings) {
    if (strings.size() != 5) {
      throw new IllegalArgumentException();
    }
    var memberAuthorities = new ArrayList<MemberType>();
    var premiumLevel = PremiumLevel.NORMAL;
    switch (strings.get(3).toLowerCase()) {
      case ("anonymous") -> {
        memberAuthorities.add(MemberType.ANONYMOUS);
      }
      case ("user") -> {
        memberAuthorities.add(MemberType.ANONYMOUS);
        memberAuthorities.add(MemberType.USER);
      }
      case ("admin") -> {
        memberAuthorities.add(MemberType.ANONYMOUS);
        memberAuthorities.add(MemberType.USER);
        memberAuthorities.add(MemberType.ADMIN);
      }
    }
    switch (strings.get(4)) {
      case ("PREMIUM") -> {
        premiumLevel = PremiumLevel.PREMIUM;
      }
      case ("SUPER_PREMIUM") -> {
        premiumLevel = PremiumLevel.SUPER_PREMIUM;
      }
    }
    this.email = strings.get(0);
    this.name = strings.get(1);
    this.userUuid = strings.get(2);
    this.memberType = memberAuthorities;
    this.premiumLevel = premiumLevel;
  }
}
