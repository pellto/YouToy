package com.pellto.youtoy.global.dto.channelManagement.response;

import com.pellto.youtoy.global.dto.channelManagement.ChannelManagementDto;
import java.util.List;

public record GetChannelManagementByMemberIdResponse(
    List<ChannelManagementDto> channelManagements
) {

}
