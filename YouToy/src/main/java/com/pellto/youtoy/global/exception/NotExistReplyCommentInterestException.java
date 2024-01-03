package com.pellto.youtoy.global.exception;

import com.pellto.youtoy.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class NotExistReplyCommentInterestException extends CustomRuntimeException {

  private static final String HANDLE_MSG = "handleNotExistReplyCommentInterestException";
  private final ErrorCode errorCode;

  public NotExistReplyCommentInterestException() {
    super(HANDLE_MSG, ErrorCode.NOT_EXIST_REPLY_COMMENT_INTEREST);
    this.errorCode = ErrorCode.NOT_EXIST_REPLY_COMMENT_INTEREST;
  }
}
