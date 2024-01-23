package com.pellto.youtoy.channel.application.adapter.in.http;

import com.pellto.youtoy.channel.domain.port.in.GetChannelInfoUsecase;
import com.pellto.youtoy.global.dto.channel.ChannelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/channels")
@RequiredArgsConstructor
public class ChannelController {

  private final GetChannelInfoUsecase getChannelInfoUsecase;

  @GetMapping("/{channelId}")
  public ChannelDto getChannel(@PathVariable Long channelId) {
    return getChannelInfoUsecase.getChannelById(channelId);
  }
}
