package com.pellto.youtoy.application.usecase;

import com.pellto.youtoy.domain.channel.service.ChannelReadService;
import com.pellto.youtoy.domain.subscribe.service.SubscribeWriteService;
import com.pellto.youtoy.domain.user.service.UserReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateChannelSubscribeUsecase {

  private final UserReadService userReadService;
  private final ChannelReadService channelReadService;
  private final SubscribeWriteService subscribeWriteService;

  // TODO: change to @Transactional
  public void execute(Long userId, Long channelId) {
    // TODO: 로직 손보기
    var user = userReadService.getUser(userId);
    var channel = channelReadService.getChannel(channelId);

    subscribeWriteService.create(user.id(), channel.id());
  }
}
