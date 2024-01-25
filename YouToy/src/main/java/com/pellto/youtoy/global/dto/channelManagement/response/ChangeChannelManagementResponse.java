package com.pellto.youtoy.global.dto.channelManagement.response;

import com.pellto.youtoy.global.dto.channelManagement.ChannelManagementDto;

public record ChangeChannelManagementResponse(
    ChannelManagementDto before,
    ChannelManagementDto after
) {

}
