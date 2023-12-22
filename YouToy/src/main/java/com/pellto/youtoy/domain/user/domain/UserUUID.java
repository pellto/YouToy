package com.pellto.youtoy.domain.user.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class UserUUID {

  private String value;

  public UserUUID(String value) {
    this.value = value;
  }
}
