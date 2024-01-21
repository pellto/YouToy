package com.pellto.youtoy.channel.domain.port.in;

import com.pellto.youtoy.channel.domain.model.Channel;

public interface ChannelCreateUsecase {

  Channel create(Long ownerId, String name);
}
