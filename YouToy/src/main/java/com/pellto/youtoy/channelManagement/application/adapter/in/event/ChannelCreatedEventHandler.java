package com.pellto.youtoy.channelManagement.application.adapter.in.event;

import com.pellto.youtoy.channelManagement.domain.port.in.CreateManagementUsecase;
import com.pellto.youtoy.global.event.channel.ChannelCreatedEvent;
import com.pellto.youtoy.global.interfaces.InboundEventAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

@InboundEventAdapter
@RequiredArgsConstructor
@Slf4j
public class ChannelCreatedEventHandler {

  private static final String HANDLER = "ChannelCreatedEventHandler";

  private final CreateManagementUsecase createManagementUsecase;

  @EventListener
  public void createOwner(ChannelCreatedEvent event) {
    log.info(String.format("[%s/createOwner]: 소유자 생성 시작(by %s) {event: %s}", HANDLER,
        event.getPublisher(), event));
    createManagementUsecase.createOwner(event.getDto().id(), event.getDto().ownerId());

    log.info(String.format("[%s/createOwner]: 소유자 생성 완료", HANDLER));
  }
}
