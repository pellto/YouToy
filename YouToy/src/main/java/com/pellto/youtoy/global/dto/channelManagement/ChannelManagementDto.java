package com.pellto.youtoy.global.dto.channelManagement;

import java.time.LocalDateTime;

public record ChannelManagementDto(
    Long id,
    Long channelId,
    Long memberId,
    String manageLevel,
    LocalDateTime createdAt
) {

}
