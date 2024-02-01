package com.pellto.youtoy.comment.domain.model;

import lombok.Getter;

@Getter
public enum CommentContentsType {
  POST("POST"), VIDEO("VIDEO"), SHORTS("SHORTS");
  private final String type;

  CommentContentsType(String type) {
    this.type = type;
  }

  public static CommentContentsType fromString(String commentContentsType) {
    switch (commentContentsType) {
      case ("POST") -> {
        return CommentContentsType.POST;
      }
      case ("VIDEO") -> {
        return CommentContentsType.VIDEO;
      }
      case ("SHORTS") -> {
        return CommentContentsType.SHORTS;
      }
      default -> {
        throw new IllegalArgumentException(
            String.format("올바른 commentsContentType(%s) 이 아닙니다.", commentContentsType));
      }
    }
  }
}