package com.pellto.youtoy.channel.application.adapter.in.http;

import com.pellto.youtoy.channel.domain.port.in.ExistChannelUsecase;
import com.pellto.youtoy.channel.domain.port.in.GetChannelInfoUsecase;
import com.pellto.youtoy.global.dto.channel.ChannelDto;
import com.pellto.youtoy.global.dto.channel.response.GetCommenterChannelInfoResponse;
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
  private final ExistChannelUsecase existChannelUsecase;

  @GetMapping("/{channelId}")
  public ChannelDto getChannel(@PathVariable Long channelId) {
    return getChannelInfoUsecase.getChannelById(channelId);
  }

  @GetMapping("/exist/{channelId}")
  public boolean existChannel(@PathVariable Long channelId) {
    return existChannelUsecase.existById(channelId);
  }

  @GetMapping("/info/{channelId}")
  public GetCommenterChannelInfoResponse getChannelInfo(@PathVariable Long channelId) {
    return getChannelInfoUsecase.getCommenterChannelInfoById(channelId);
  }
}
