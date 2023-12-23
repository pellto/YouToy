package com.pellto.youtoy.domain.channel.exception;

import com.pellto.youtoy.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class NotExistChannelException extends RuntimeException {

  private final String handleMsg = "handleNotNotExistChannelException";
  private final ErrorCode errorCode;

  public NotExistChannelException() {
    super(ErrorCode.NOT_EXIST_CHANNEL.getMessage());
    this.errorCode = ErrorCode.NOT_EXIST_CHANNEL;
  }

  public NotExistChannelException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }
}
