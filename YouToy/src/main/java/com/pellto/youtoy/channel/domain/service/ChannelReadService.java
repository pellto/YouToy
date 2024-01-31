package com.pellto.youtoy.channel.domain.service;

import com.pellto.youtoy.channel.domain.port.in.ExistChannelUsecase;
import com.pellto.youtoy.channel.domain.port.in.GetChannelInfoUsecase;
import com.pellto.youtoy.channel.domain.port.out.LoadChannelPort;
import com.pellto.youtoy.global.dto.channel.ChannelDto;
import com.pellto.youtoy.global.dto.channel.response.GetCommenterChannelInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChannelReadService implements GetChannelInfoUsecase, ExistChannelUsecase {

  private final LoadChannelPort loadChannelPort;

  @Override
  public boolean existById(Long channelId) {
    return loadChannelPort.existById(channelId);
  }

  @Override
  public ChannelDto getChannelById(Long channelId) {
    var channel = loadChannelPort.load(channelId);
    return channel.toDto();
  }

  @Override
  public GetCommenterChannelInfoResponse getCommenterChannelInfoById(Long channelId) {
    var channel = loadChannelPort.load(channelId);
    return GetCommenterChannelInfoResponse.builder()
        .commenterId(channel.getId())
        .commenterHandle(channel.getHandle().value())
        .displayName(channel.getChannelInfo().getDisplayName())
        .profilePath(channel.getChannelInfo().getProfilePath())
        .build();
  }
}
