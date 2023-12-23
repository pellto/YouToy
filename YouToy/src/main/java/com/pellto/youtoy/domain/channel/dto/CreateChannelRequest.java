package com.pellto.youtoy.domain.channel.dto;

import com.pellto.youtoy.domain.channel.domain.ChannelInfo;

public record CreateChannelRequest(
    ChannelInfo channelInfo
) {

}
