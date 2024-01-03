package com.pellto.youtoy.domain.community.exception;

import com.pellto.youtoy.global.error.ErrorCode;
import com.pellto.youtoy.global.exception.CustomRuntimeException;
import lombok.Getter;

@Getter
public class NotExistPostInterestException extends CustomRuntimeException {

  private static final String HANDLE_MSG = "handleNotExistPostInterestException";
  private final ErrorCode errorCode;

  public NotExistPostInterestException() {
    super(HANDLE_MSG, ErrorCode.NOT_EXIST_COMMUNITY_POST_INTEREST);
    this.errorCode = ErrorCode.NOT_EXIST_COMMUNITY_POST_INTEREST;
  }
}
