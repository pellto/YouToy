package com.pellto.youtoy.global.exception;

import com.pellto.youtoy.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class NotExistReplyInterestException extends CustomRuntimeException {

  private static final String HANDLE_MSG = "handleNotExistReplyInterestException";
  private final ErrorCode errorCode;

  public NotExistReplyInterestException() {
    super(HANDLE_MSG, ErrorCode.NOT_EXIST_REPLY_INTEREST);
    this.errorCode = ErrorCode.NOT_EXIST_REPLY_INTEREST;
  }
}
