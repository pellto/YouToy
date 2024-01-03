package com.pellto.youtoy.domain.user.exception;

import com.pellto.youtoy.global.error.ErrorCode;
import com.pellto.youtoy.global.exception.CustomRuntimeException;
import lombok.Getter;

@Getter
public class WrongRepeatPasswordException extends CustomRuntimeException {

  private static final String HANDLE_MSG = "handleWrongRepeatPasswordException";
  private final ErrorCode errorCode = ErrorCode.WRONG_REPEAT_PASSWORD;

  public WrongRepeatPasswordException() {
    super(HANDLE_MSG, ErrorCode.WRONG_REPEAT_PASSWORD);
  }
}
