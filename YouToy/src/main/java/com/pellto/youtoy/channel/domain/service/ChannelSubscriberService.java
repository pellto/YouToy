package com.pellto.youtoy.channel.domain.service;

import com.pellto.youtoy.channel.domain.port.in.IncrementSubscriberUsecase;
import com.pellto.youtoy.channel.domain.port.out.LoadChannelPort;
import com.pellto.youtoy.channel.domain.port.out.SaveChannelPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChannelSubscriberService implements IncrementSubscriberUsecase {

  private final SaveChannelPort saveChannelPort;
  private final LoadChannelPort loadChannelPort;

  @Override
  public void increase(Long channelId) {
    var channel = loadChannelPort.load(channelId);
    channel.increaseSubscriber();
    saveChannelPort.save(channel);
  }

  @Override
  public void decrease(Long channelId) {
    var channel = loadChannelPort.load(channelId);
    channel.decreaseSubscriber();
    saveChannelPort.save(channel);
  }
}
