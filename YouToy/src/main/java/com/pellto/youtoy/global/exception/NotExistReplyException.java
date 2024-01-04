package com.pellto.youtoy.global.exception;

import com.pellto.youtoy.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class NotExistReplyException extends CustomRuntimeException {

  private static final String HANDLE_MSG = "handleNotExistReplyException";
  private final ErrorCode errorCode;

  public NotExistReplyException() {
    super(HANDLE_MSG, ErrorCode.NOT_EXIST_REPLY);
    this.errorCode = ErrorCode.NOT_EXIST_REPLY;
  }
}
