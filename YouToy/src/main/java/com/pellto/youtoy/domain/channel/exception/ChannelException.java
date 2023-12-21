package com.pellto.youtoy.domain.channel.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum ChannelException {
  NOT_EXIST_CHANNEL(404, "CH001", "해당 채널이 존재하지 않습니다.");
  
  private final String code;
  private final String message;
  private final int status;

  ChannelException(final int status, final String code, final String message) {
    this.status = status;
    this.message = message;
    this.code = code;
  }
}
