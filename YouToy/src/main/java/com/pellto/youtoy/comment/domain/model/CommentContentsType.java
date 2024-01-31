package com.pellto.youtoy.comment.domain.model;

import lombok.Getter;

@Getter
public enum CommentContentsType {
  POST("POST"), VIDEO("VIDEO"), SHORTS("SHORTS");
  private final String type;

  CommentContentsType(String type) {
    this.type = type;
  }
}