package com.pellto.youtoy.domain.channel.exception;

import com.pellto.youtoy.global.error.CustomRuntimeException;
import com.pellto.youtoy.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class NotExistSubscriberChannelException extends CustomRuntimeException {

  private static final String HANDLE_MSG = "handleNotNotExistSubscriberChannelException";
  private final ErrorCode errorCode;

  public NotExistSubscriberChannelException() {
    super(HANDLE_MSG, ErrorCode.NOT_EXIST_SUBSCRIBER_CHANNEL);
    this.errorCode = ErrorCode.NOT_EXIST_SUBSCRIBER_CHANNEL;
  }

  public NotExistSubscriberChannelException(ErrorCode errorCode) {
    super(HANDLE_MSG, errorCode);
    this.errorCode = errorCode;
  }
}
