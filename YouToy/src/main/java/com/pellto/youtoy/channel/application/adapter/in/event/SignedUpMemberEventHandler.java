package com.pellto.youtoy.channel.application.adapter.in.event;

import com.pellto.youtoy.channel.domain.port.in.ChannelCreateUsecase;
import com.pellto.youtoy.global.event.member.SignedUpEvent;
import com.pellto.youtoy.global.interfaces.InboundEventAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

@InboundEventAdapter
@RequiredArgsConstructor
@Slf4j
public class SignedUpMemberEventHandler {

  private final ChannelCreateUsecase channelCreateUsecase;

  @EventListener
  public void create(SignedUpEvent event) throws InterruptedException {
    channelCreateUsecase.create(event.getMemberId(), event.getMemberName());
    log.info(String.format("[SignedUpMemberEventHandler/create]: 채널 생성 완료 {event: %s}", event));
  }
}
