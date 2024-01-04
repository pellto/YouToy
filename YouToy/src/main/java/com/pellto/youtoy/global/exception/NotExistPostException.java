package com.pellto.youtoy.global.exception;

import com.pellto.youtoy.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class NotExistPostException extends CustomRuntimeException {

  private static final String HANDLE_MSG = "handleNotExistPostException";
  private final ErrorCode errorCode;

  public NotExistPostException() {
    super(HANDLE_MSG, ErrorCode.NOT_EXIST_POST);
    this.errorCode = ErrorCode.NOT_EXIST_POST;
  }
}
