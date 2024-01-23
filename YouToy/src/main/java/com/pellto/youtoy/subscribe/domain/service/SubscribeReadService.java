package com.pellto.youtoy.subscribe.domain.service;

import com.pellto.youtoy.subscribe.domain.model.Subscribe;
import com.pellto.youtoy.subscribe.domain.port.in.GetSubscribedChannelUsecase;
import com.pellto.youtoy.subscribe.domain.port.out.LoadSubscribePort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscribeReadService implements GetSubscribedChannelUsecase {

  private final LoadSubscribePort loadSubscribePort;

  @Override
  public List<Long> getChannels(Long subscriberId) {
    var subscribes = loadSubscribePort.loadsBySubscriberId(subscriberId);
    return subscribes.stream().map(Subscribe::getSubscribedChannelId).toList();
  }
}
