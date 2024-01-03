package com.pellto.youtoy.global.exception;

import com.pellto.youtoy.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class NotExistReplyCommentException extends CustomRuntimeException {

  private static final String HANDLE_MSG = "handleNotExistReplyCommentException";
  private final ErrorCode errorCode;

  public NotExistReplyCommentException() {
    super(HANDLE_MSG, ErrorCode.NOT_EXIST_REPLY_COMMENT);
    this.errorCode = ErrorCode.NOT_EXIST_REPLY_COMMENT;
  }
}
