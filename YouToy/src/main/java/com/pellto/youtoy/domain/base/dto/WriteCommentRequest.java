package com.pellto.youtoy.domain.base.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record WriteCommentRequest(
    @NotNull(message = "id 는 필수 입니다.")
    @Positive(message = "유효하지 않은 id 패턴 입니다.")
    Long contentId,
    @NotBlank(message = "내용이 입력되지 않았습니다.")
    @Size(max = 1024)
    String content,
    @NotNull(message = "작성자 uuid는 필수 입니다.")
    @Pattern(
        regexp = "^[a-zA-Z0-9]{10}-[a-zA-Z0-9]{10}-[a-zA-Z0-9]{20}$",
        message = "유효하지 않은 uuid 패턴 입니다."
    )
    String commenterUuid
) {

}
