package com.pellto.youtoy.global.exception;

import com.pellto.youtoy.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class NotExistCommentException extends CustomRuntimeException {

  private static final String HANDLE_MSG = "handleNotExistCommentException";
  private final ErrorCode errorCode;

  public NotExistCommentException() {
    super(HANDLE_MSG, ErrorCode.NOT_EXIST_COMMENT);
    this.errorCode = ErrorCode.NOT_EXIST_COMMENT;
  }
}
