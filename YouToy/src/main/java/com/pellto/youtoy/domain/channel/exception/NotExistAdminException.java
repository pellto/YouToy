package com.pellto.youtoy.domain.channel.exception;

import com.pellto.youtoy.global.error.ErrorCode;
import com.pellto.youtoy.global.exception.CustomRuntimeException;
import lombok.Getter;

@Getter
public class NotExistAdminException extends CustomRuntimeException {

  private static final String HANDLE_MSG = "handleNotExistAdminException";
  private final ErrorCode errorCode;

  public NotExistAdminException() {
    super(HANDLE_MSG, ErrorCode.NOT_EXIST_ADMIN);
    this.errorCode = ErrorCode.NOT_EXIST_ADMIN;
  }

  public NotExistAdminException(ErrorCode errorCode) {
    super(HANDLE_MSG, errorCode);
    this.errorCode = errorCode;
  }
}
