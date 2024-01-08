package com.pellto.youtoy.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserSignInRequest(
    @Email(message = "올바른 email 형식이 아닙니다.")
    String email,
    @Size(min = 8, message = "비밀번호는 8자 이상이어야 합니다.")
    @Size(max = 32, message = "비밀번호는 32자 이하이어야 합니다.")
    String pwd
) {

}
