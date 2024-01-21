package com.pellto.youtoy.channel.domain.service;

import com.pellto.youtoy.channel.domain.model.Channel;
import com.pellto.youtoy.channel.domain.model.ChannelInfo;
import com.pellto.youtoy.channel.domain.port.in.ChannelCreateUsecase;
import com.pellto.youtoy.channel.domain.port.out.SaveChannelPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChannelWriteService implements ChannelCreateUsecase {

  private final SaveChannelPort saveChannelPort;

  @Override
  public Channel create(Long ownerId, String name) {
    var channelInfo = ChannelInfo.builder()
        .displayName(name)
        .build();
    var channel = Channel.builder()
        .ownerId(ownerId)
        .channelInfo(channelInfo)
        .build();

    channel = saveChannelPort.save(channel);

    // TODO: Publish Channel Created Event
    return channel;

  }
}
