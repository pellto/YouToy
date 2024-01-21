package com.pellto.youtoy.channel.domain.port.out;

import com.pellto.youtoy.channel.domain.model.Channel;

public interface LoadChannelPort {

  Channel load(Long channelId);

}
