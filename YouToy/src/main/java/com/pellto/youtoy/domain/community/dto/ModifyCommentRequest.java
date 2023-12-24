package com.pellto.youtoy.domain.community.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ModifyCommentRequest(

    @NotNull(message = "id 는 필수 입니다.")
    @Positive(message = "유효하지 않은 id 패턴 입니다.")
    Long id,
    @NotBlank(message = "내용이 입력되지 않았습니다.")
    @Size(max = 1024)
    String content
) {

}
