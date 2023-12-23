package com.pellto.youtoy.domain.user.dto;

import com.pellto.youtoy.domain.user.domain.UserInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record UserSignUpRequest(
    @NotNull(message = "유저 정보는 필수 입니다.")
    @Valid
    UserInfo userInfo,
    @NotNull(message = "재확인 비밀 번호는 필수 입니다.")
    String repeatPwd
) {

}
