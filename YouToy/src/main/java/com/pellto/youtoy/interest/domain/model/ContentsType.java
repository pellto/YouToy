package com.pellto.youtoy.interest.domain.model;

import lombok.Getter;

@Getter
public enum ContentsType {
  POST("POST"), COMMENT("COMMENT"), REPLIED("REPLIED"), VIDEO("VIDEO"), SHORTS("SHORTS");
  private final String type;

  ContentsType(String type) {
    this.type = type;
  }
}
