package com.pellto.youtoy.channel.domain.port.out;

import com.pellto.youtoy.channel.domain.model.Channel;

public interface SaveChannelPort {

  void delete(Channel channel);

  Channel save(Channel channel);

}
