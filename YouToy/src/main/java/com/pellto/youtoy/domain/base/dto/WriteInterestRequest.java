package com.pellto.youtoy.domain.base.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record WriteInterestRequest(
    @NotNull(message = "id 는 필수 입니다.")
    @Positive(message = "유효하지 않은 id 패턴 입니다.")
    Long contentsId,
    @NotNull(message = "작성자 uuid는 필수 입니다.")
    @Pattern(
        regexp = "^[a-zA-Z0-9]{10}-[a-zA-Z0-9]{10}-[a-zA-Z0-9]{20}$",
        message = "유효하지 않은 uuid 패턴 입니다."
    )
    String interestingUserUuid,
    boolean dislike
) {

}
