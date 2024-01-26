package com.pellto.youtoy.auth.domain.model;


import lombok.Builder;
import lombok.Getter;

// TODO: Define Auth POJO
@Getter
public class Auth {

  private final Object infos;

  @Builder
  public Auth(Object infos) {
    this.infos = infos;
  }

  // TODO: Issue token with JWT Provider
  public String createToken() {
    return this.infos.toString();
  }
}
