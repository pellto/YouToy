package com.pellto.youtoy.domain.user.exception;

import com.pellto.youtoy.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class WrongRepeatPasswordException extends RuntimeException {

  private final String handleMsg = "handleWrongRepeatPasswordException";
  private final ErrorCode errorCode = ErrorCode.WRONG_REPEAT_PASSWORD;

  public WrongRepeatPasswordException() {
    super(ErrorCode.WRONG_REPEAT_PASSWORD.getMessage());
  }
}
