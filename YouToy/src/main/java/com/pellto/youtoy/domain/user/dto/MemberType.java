package com.pellto.youtoy.domain.user.dto;

import org.springframework.security.core.GrantedAuthority;

public enum MemberType implements GrantedAuthority {
  ANONYMOUS("anonymous"), USER("user"), ADMIN("admin");

  private final String authority;

  MemberType(String authority) {
    this.authority = authority;
  }

  @Override
  public String getAuthority() {
    return this.authority;
  }
}
