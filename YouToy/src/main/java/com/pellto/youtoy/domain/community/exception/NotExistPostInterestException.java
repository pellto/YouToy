package com.pellto.youtoy.domain.community.exception;

import com.pellto.youtoy.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class NotExistPostInterestException extends RuntimeException {

  private final String handleMsg = "handleNotExistPostInterestException";
  private final ErrorCode errorCode;

  public NotExistPostInterestException() {
    super(ErrorCode.NOT_EXIST_COMMUNITY_POST_INTEREST.getMessage());
    this.errorCode = ErrorCode.NOT_EXIST_COMMUNITY_POST_INTEREST;
  }
}
