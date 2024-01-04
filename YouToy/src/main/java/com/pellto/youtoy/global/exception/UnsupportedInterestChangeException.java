package com.pellto.youtoy.global.exception;

import com.pellto.youtoy.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class UnsupportedInterestChangeException extends CustomRuntimeException {

  private static final String HANDLE_MSG = "handleUnsupportedInterestChangeException";
  private final ErrorCode errorCode;

  public UnsupportedInterestChangeException() {
    super(HANDLE_MSG, ErrorCode.UNSUPPORTED_INTEREST_CHANGE_EXCEPTION);
    this.errorCode = ErrorCode.UNSUPPORTED_INTEREST_CHANGE_EXCEPTION;
  }
}
