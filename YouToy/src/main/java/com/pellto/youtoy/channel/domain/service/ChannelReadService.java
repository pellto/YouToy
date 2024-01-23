package com.pellto.youtoy.channel.domain.service;

import com.pellto.youtoy.channel.domain.port.in.GetChannelInfoUsecase;
import com.pellto.youtoy.channel.domain.port.out.LoadChannelPort;
import com.pellto.youtoy.global.dto.channel.ChannelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChannelReadService implements GetChannelInfoUsecase {

  private final LoadChannelPort loadChannelPort;

  @Override
  public ChannelDto getChannelById(Long channelId) {
    var channel = loadChannelPort.load(channelId);
    return channel.toDto();
  }
}
