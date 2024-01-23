package com.pellto.youtoy.subscribe.domain.port.out;

import com.pellto.youtoy.global.dto.subscribe.SubscribeDto;

public interface SubscribeEventPort {

  void subscribedChannel(SubscribeDto dto);

  void unsubscribedChannel(SubscribeDto dto);
}
