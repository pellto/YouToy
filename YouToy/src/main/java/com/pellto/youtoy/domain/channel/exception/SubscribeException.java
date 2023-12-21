package com.pellto.youtoy.domain.channel.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum SubscribeException {
  NOT_EXIST_SUBSCRIBER_CHANNEL(404, "CH001", "구독자 채널이 존재하지 않습니다."),
  NOT_EXIST_SUBSCRIBED_CHANNEL(404, "CH002", "구독할 채널이 존재하지 않습니다.");

  private final String code;
  private final String message;
  private final int status;

  SubscribeException(final int status, final String code, final String message) {
    this.status = status;
    this.message = message;
    this.code = code;
  }
}
