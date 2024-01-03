package com.pellto.youtoy.domain.base.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ModifyInterestRequest(
    @NotNull(message = "id 는 필수 입니다.")
    @Positive(message = "유효하지 않은 id 패턴 입니다.")
    Long id,
    boolean dislike
) {

}
