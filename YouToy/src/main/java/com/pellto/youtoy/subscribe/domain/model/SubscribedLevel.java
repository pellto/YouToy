package com.pellto.youtoy.subscribe.domain.model;

import lombok.Getter;

@Getter
public enum SubscribedLevel {
  ALL("ALL"), CUSTOM("CUSTOM"), NONE("NONE");

  private final String value;

  SubscribedLevel(String value) {
    this.value = value;
  }
}
