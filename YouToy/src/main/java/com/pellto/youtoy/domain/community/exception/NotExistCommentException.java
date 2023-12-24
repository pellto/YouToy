package com.pellto.youtoy.domain.community.exception;

import com.pellto.youtoy.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class NotExistCommentException extends RuntimeException {

  private final String handleMsg = "handleNotExistCommunityCommentException";
  private final ErrorCode errorCode;

  public NotExistCommentException() {
    super(ErrorCode.NOT_EXIST_COMMUNITY_COMMENT.getMessage());
    this.errorCode = ErrorCode.NOT_EXIST_COMMUNITY_COMMENT;
  }
}
