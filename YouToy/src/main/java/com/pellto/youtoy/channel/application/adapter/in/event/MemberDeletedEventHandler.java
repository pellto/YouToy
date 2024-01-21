package com.pellto.youtoy.channel.application.adapter.in.event;

import com.pellto.youtoy.channel.domain.port.in.ChannelDeleteUsecase;
import com.pellto.youtoy.global.event.member.MemberDeletedEvent;
import com.pellto.youtoy.global.interfaces.InboundEventAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

@InboundEventAdapter("MemberDeletedEventHandlerInChannel")
@RequiredArgsConstructor
@Slf4j
public class MemberDeletedEventHandler {

  private final ChannelDeleteUsecase channelDeleteUsecase;

  @EventListener
  public void delete(MemberDeletedEvent event) throws InterruptedException {
    log.info(String.format("[MemberDeletedEventHandler/delete]: 채널 삭제 시작(by %s) {dto: %s}",
        event.getPublisher(), event.getDto()));
    channelDeleteUsecase.delete(event.getDto());
  }
}
