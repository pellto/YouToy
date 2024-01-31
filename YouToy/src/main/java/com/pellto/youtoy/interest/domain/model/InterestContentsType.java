package com.pellto.youtoy.interest.domain.model;

import lombok.Getter;

@Getter
public enum InterestContentsType {
  POST("POST"), COMMENT("COMMENT"), REPLIED("REPLIED"), VIDEO("VIDEO"), SHORTS("SHORTS");
  private final String type;

  InterestContentsType(String type) {
    this.type = type;
  }
}
