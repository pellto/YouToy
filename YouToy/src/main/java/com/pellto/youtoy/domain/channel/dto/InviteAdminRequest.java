package com.pellto.youtoy.domain.channel.dto;

import com.pellto.youtoy.domain.channel.domain.AuthLevel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record InviteAdminRequest(
    @NotNull(message = "초대할 유저의 uuid는 필수 입니다.")
    @Pattern(
        regexp = "^[a-zA-Z0-9]{10}-[a-zA-Z0-9]{10}-[a-zA-Z0-9]{20}$",
        message = "유효한 uuid 패턴이 아닙니다."
    )
    String userUuid,
    @Positive(message = "id 는 양수여야합니다.")
    Long targetChannelId,
    AuthLevel authLevel
) {

}
