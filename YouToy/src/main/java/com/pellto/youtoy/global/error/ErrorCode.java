package com.pellto.youtoy.global.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum ErrorCode {
  // User
  WRONG_REPEAT_PASSWORD(404, "US001", "재입력한 비밀번호가 틀립니다."),
  // Subscribe
  NOT_EXIST_SUBSCRIBER_CHANNEL(404, "SB001", "구독자 채널이 존재하지 않습니다."),
  NOT_EXIST_SUBSCRIBED_CHANNEL(404, "SB002", "구독할 채널이 존재하지 않습니다."),
  // Channel
  NOT_EXIST_CHANNEL(404, "CH001", "해당 채널이 존재하지 않습니다."),
  NOT_EXIST_ADMIN(404, "CA001", "해당 어드민이 존재하지 않습니다."),
  // Community Comment
  NOT_EXIST_COMMUNITY_COMMENT(404, "CM001", "해당 댓글이 존재하지 않습니다.");

  private final int status;
  private final String code;
  private final String message;

  ErrorCode(final int status, final String code, final String message) {
    this.status = status;
    this.message = message;
    this.code = code;
  }
}
