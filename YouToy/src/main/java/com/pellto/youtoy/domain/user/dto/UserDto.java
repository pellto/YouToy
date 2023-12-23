package com.pellto.youtoy.domain.user.dto;

import com.pellto.youtoy.domain.user.domain.PremiumLevel;
import com.pellto.youtoy.domain.user.domain.UserInfo;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import java.time.LocalDateTime;

public record UserDto(
    UserUUID uuid,
    UserInfo userInfo,
    PremiumLevel premiumLevel,
    LocalDateTime createdAt
) {

}
