package com.pellto.youtoy.membership.domain.model;

import lombok.Getter;

@Getter
public enum Premium {
  NORMAL("NORMAL"), PREMIUM("PREMIUM");
  private final String value;

  Premium(String value) {
    this.value = value;
  }
}
