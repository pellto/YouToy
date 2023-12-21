package com.pellto.youtoy.domain.channel.application;

import com.pellto.youtoy.domain.channel.domain.Channel;
import com.pellto.youtoy.domain.channel.dto.ChannelDto;
import com.pellto.youtoy.domain.channel.dto.CreateChannelRequest;
import com.pellto.youtoy.domain.channel.repository.ChannelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChannelWriteService {

  private final ChannelRepository channelRepository;
  private final ChannelReadService channelReadService;

  public ChannelDto create(CreateChannelRequest req) {
    var channel = Channel.builder()
        .handle(req.handle())
        .displayName(req.displayName())
        .description(req.description())
        .banner(req.banner())
        .profile(req.profile())
        .build();
    return channelReadService.toDto(channelRepository.save(channel));
  }

  public void delete(Long id) {
    channelRepository.deleteById(id);
  }
}
