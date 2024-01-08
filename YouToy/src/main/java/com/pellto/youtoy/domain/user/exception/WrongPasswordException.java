package com.pellto.youtoy.domain.user.exception;

import com.pellto.youtoy.global.error.ErrorCode;
import com.pellto.youtoy.global.exception.CustomRuntimeException;
import lombok.Getter;

@Getter
public class WrongPasswordException extends CustomRuntimeException {

  private static final String HANDLE_MSG = "handleWrongPasswordException";
  private final ErrorCode errorCode = ErrorCode.WRONG_PASSWORD;

  public WrongPasswordException() {
    super(HANDLE_MSG, ErrorCode.WRONG_PASSWORD);
  }
}
