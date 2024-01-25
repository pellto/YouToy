package com.pellto.youtoy.global.dto.channelManagement.request;

public record ChangeChannelManagementRequest(
    Long channelId,
    Long memberId,
    String level
) {

}
