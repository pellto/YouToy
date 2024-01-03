package com.pellto.youtoy.domain.channel.exception;

import com.pellto.youtoy.global.error.ErrorCode;
import com.pellto.youtoy.global.exception.CustomRuntimeException;
import lombok.Getter;

@Getter
public class NotExistSubscribedChannelException extends CustomRuntimeException {

  private static final String HANDLE_MSG = "handleNotNotExistSubscribedChannelException";
  private final ErrorCode errorCode;

  public NotExistSubscribedChannelException() {
    super(HANDLE_MSG, ErrorCode.NOT_EXIST_SUBSCRIBED_CHANNEL);
    this.errorCode = ErrorCode.NOT_EXIST_SUBSCRIBED_CHANNEL;
  }

  public NotExistSubscribedChannelException(ErrorCode errorCode) {
    super(HANDLE_MSG, errorCode);
    this.errorCode = errorCode;
  }
}
