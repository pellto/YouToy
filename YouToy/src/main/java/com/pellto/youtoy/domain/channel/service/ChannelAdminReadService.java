package com.pellto.youtoy.domain.channel.service;

import com.pellto.youtoy.domain.channel.repository.ChannelAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChannelAdminReadService {

  private final ChannelAdminRepository channelAdminRepository;

  public boolean isAdmin(Long channelId, Long userId) {
    return channelAdminRepository.findByChannelIdAndUserId(channelId, userId).isPresent();
  }
}
