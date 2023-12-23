package com.pellto.youtoy.domain.channel.exception;

import com.pellto.youtoy.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class NotExistAdminException extends RuntimeException {

  private final String handleMsg = "handleNotExistAdminException";
  private final ErrorCode errorCode;

  public NotExistAdminException() {
    super(ErrorCode.NOT_EXIST_ADMIN.getMessage());
    this.errorCode = ErrorCode.NOT_EXIST_ADMIN;
  }

  public NotExistAdminException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }
}
