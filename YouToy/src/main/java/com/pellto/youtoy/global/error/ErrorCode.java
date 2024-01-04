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
  // Community Post
  NOT_EXIST_COMMUNITY_POST_INTEREST(404, "CPI001", "해당 관심이 존재하지 않습니다."),
  NOT_EXIST_POST(404, "P001", "해당 포스트가 존재하지 않습니다."),

  // Base
  NOT_EXIST_COMMENT(404, "CM001", "해당 댓글이 존재하지 않습니다."),
  NOT_EXIST_COMMENT_INTEREST(404, "RC001", "해당 관심이 존재하지 않습니다."),
  NOT_EXIST_REPLY(404, "CR001", "해당 답글이 존재하지 않습니다."),
  NOT_EXIST_REPLY_INTEREST(404, "CRI001", "해당 관심이 존재하지 않습니다."),
  NOT_EXIST_CONTENTS_INTEREST(404, "CI001", "해당 관심이 존재하지 않습니다."),
  UNSUPPORTED_INTEREST_CHANGE_EXCEPTION(406, "I001", "지원하지 않는 변경 유형입니다.");

  private final int status;
  private final String code;
  private final String message;

  ErrorCode(final int status, final String code, final String message) {
    this.status = status;
    this.message = message;
    this.code = code;
  }
}
