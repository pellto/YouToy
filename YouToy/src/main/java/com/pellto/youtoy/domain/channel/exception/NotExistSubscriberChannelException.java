package com.pellto.youtoy.domain.channel.exception;

import com.pellto.youtoy.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class NotExistSubscriberChannelException extends RuntimeException {

  private final String handleMsg = "handleNotNotExistSubscriberChannelException";
  private final ErrorCode errorCode;

  public NotExistSubscriberChannelException() {
    super(ErrorCode.NOT_EXIST_SUBSCRIBER_CHANNEL.getMessage());
    this.errorCode = ErrorCode.NOT_EXIST_SUBSCRIBER_CHANNEL;
  }

  public NotExistSubscriberChannelException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }
}
