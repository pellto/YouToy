package com.pellto.youtoy.global.exception;

import com.pellto.youtoy.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class NotExistCommentInterestException extends CustomRuntimeException {

  private static final String HANDLE_MSG = "handleNotExistCommentInterestException";
  private final ErrorCode errorCode;

  public NotExistCommentInterestException() {
    super(HANDLE_MSG, ErrorCode.NOT_EXIST_COMMENT_INTEREST);
    this.errorCode = ErrorCode.NOT_EXIST_COMMENT_INTEREST;
  }
}