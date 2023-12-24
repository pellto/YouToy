package com.pellto.youtoy.domain.community.dto;

import com.pellto.youtoy.domain.user.domain.UserUUID;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


public record InterestPostRequest(
    @NotNull(message = "작성자 uuid는 필수 입니다.")
    @Pattern(
        regexp = "^[a-zA-Z0-9]{10}-[a-zA-Z0-9]{10}-[a-zA-Z0-9]{20}$",
        message = "유효하지 않은 uuid 패턴 입니다."
    )
    UserUUID userUuid,
    @NotNull(message = "관심 유형은 필수 입니다.")
    boolean dislike
) {

}
