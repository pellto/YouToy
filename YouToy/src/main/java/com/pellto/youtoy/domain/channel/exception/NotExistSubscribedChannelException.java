package com.pellto.youtoy.domain.channel.exception;

import com.pellto.youtoy.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class NotExistSubscribedChannelException extends RuntimeException {

  private final String handleMsg = "handleNotNotExistSubscribedChannelException";
  private final ErrorCode errorCode;

  public NotExistSubscribedChannelException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }
}
