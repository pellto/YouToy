package com.pellto.youtoy.channel.domain.port.out;

import com.pellto.youtoy.global.dto.channel.ChannelDto;

public interface ChannelEventPort {

  void channelDeletedEvent(ChannelDto dto);
}
