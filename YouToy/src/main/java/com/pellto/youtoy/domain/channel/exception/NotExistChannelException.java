package com.pellto.youtoy.domain.channel.exception;

import com.pellto.youtoy.global.error.ErrorCode;
import com.pellto.youtoy.global.exception.CustomRuntimeException;
import lombok.Getter;

@Getter
public class NotExistChannelException extends CustomRuntimeException {

  private static final String HANDLE_MSG = "handleNotNotExistChannelException";
  private final ErrorCode errorCode;

  public NotExistChannelException() {
    super(HANDLE_MSG, ErrorCode.NOT_EXIST_CHANNEL);
    this.errorCode = ErrorCode.NOT_EXIST_CHANNEL;
  }

  public NotExistChannelException(ErrorCode errorCode) {
    super(HANDLE_MSG, errorCode);
    this.errorCode = errorCode;
  }
}
