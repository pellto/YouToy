package com.pellto.youtoy.global.exception;

import com.pellto.youtoy.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class NotExistContentsInterestException extends CustomRuntimeException {

  private static final String HANDLE_MSG = "handleNotExistContentsInterestException";
  private final ErrorCode errorCode;

  public NotExistContentsInterestException() {
    super(HANDLE_MSG, ErrorCode.NOT_EXIST_CONTENTS_INTEREST);
    this.errorCode = ErrorCode.NOT_EXIST_CONTENTS_INTEREST;
  }
}
