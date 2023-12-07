package com.pellto.youtoy.application.controller;

import com.pellto.youtoy.application.usecase.CancelChannelSubscribeUsecase;
import com.pellto.youtoy.application.usecase.CreateChannelSubscribeUsecase;
import com.pellto.youtoy.domain.subscribe.dto.CancelSubscribeCommand;
import com.pellto.youtoy.domain.subscribe.dto.CreateSubscribeCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/subscribe")
public class SubscribeController {

  private final CreateChannelSubscribeUsecase createChannelSubscribeUsecase;
  private final CancelChannelSubscribeUsecase cancelChannelSubscribeUsecase;

  @PostMapping
  public void subscribe(@RequestBody CreateSubscribeCommand cmd) {
    createChannelSubscribeUsecase.execute(cmd.userId(), cmd.channelId());
  }

  @DeleteMapping
  public void unSubscribe(@RequestBody CancelSubscribeCommand cmd) {
    cancelChannelSubscribeUsecase.execute(cmd.userId(), cmd.channelId());
  }
}
