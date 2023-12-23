package com.pellto.youtoy.domain.user.dto;

import com.pellto.youtoy.domain.user.domain.UserInfo;

public record UserSignUpRequest(
    UserInfo userInfo,
    String repeatPwd
) {

}
