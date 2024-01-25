package com.pellto.youtoy.channel.domain.service;

import com.pellto.youtoy.channel.domain.model.Channel;
import com.pellto.youtoy.channel.domain.model.ChannelInfo;
import com.pellto.youtoy.channel.domain.port.in.ChannelCreateUsecase;
import com.pellto.youtoy.channel.domain.port.in.ChannelDeleteUsecase;
import com.pellto.youtoy.channel.domain.port.out.ChannelEventPort;
import com.pellto.youtoy.channel.domain.port.out.LoadChannelPort;
import com.pellto.youtoy.channel.domain.port.out.SaveChannelPort;
import com.pellto.youtoy.global.dto.member.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChannelWriteService implements ChannelCreateUsecase, ChannelDeleteUsecase {

  private final LoadChannelPort loadChannelPort;
  private final SaveChannelPort saveChannelPort;
  private final ChannelEventPort channelEventPort;

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
    channelEventPort.channelCreatedEvent(channel.toDto());
    return channel;

  }

  @Override
  public void delete(MemberDto memberDto) {
    var channel = loadChannelPort.load(memberDto.id());
    saveChannelPort.delete(channel);

    channelEventPort.channelDeletedEvent(channel.toDto());
  }
}
