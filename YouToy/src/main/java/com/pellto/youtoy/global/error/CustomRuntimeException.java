package com.pellto.youtoy.global.error;

import lombok.Getter;

@Getter
public class CustomRuntimeException extends RuntimeException {

  private final String handleMsg;
  private final ErrorCode errorCode;

  protected CustomRuntimeException(String handleMsg, ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.handleMsg = handleMsg;
    this.errorCode = errorCode;
  }
}
