package com.pellto.youtoy.domain.user.exception;

import com.pellto.youtoy.global.error.ErrorCode;
import com.pellto.youtoy.global.exception.CustomRuntimeException;
import lombok.Getter;

@Getter
public class NotExistUserException extends CustomRuntimeException {

  private static final String HANDLE_MSG = "handleNotExistUserException";
  private final ErrorCode errorCode = ErrorCode.NOT_EXIST_USER;

  public NotExistUserException() {
    super(HANDLE_MSG, ErrorCode.NOT_EXIST_USER);
  }
}
