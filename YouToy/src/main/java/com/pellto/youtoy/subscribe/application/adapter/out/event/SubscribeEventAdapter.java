package com.pellto.youtoy.subscribe.application.adapter.out.event;

import com.pellto.youtoy.global.dto.subscribe.SubscribeDto;
import com.pellto.youtoy.global.event.subscribe.SubscribedChannelEvent;
import com.pellto.youtoy.global.event.subscribe.UnsubscribedChannelEvent;
import com.pellto.youtoy.global.interfaces.OutboundEventAdapter;
import com.pellto.youtoy.subscribe.domain.port.out.SubscribeEventPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

@OutboundEventAdapter
@RequiredArgsConstructor
@Slf4j
public class SubscribeEventAdapter implements SubscribeEventPort {

  private static final String PUBLISHER = "SubscribeEventAdapter";

  private final ApplicationEventPublisher applicationEventPublisher;

  @Override
  public void subscribedChannel(SubscribeDto dto) {
    var publisher = PUBLISHER + "/subscribedChannel";
    log.info(String.format("[%s]: 구독(subscribe) 완료 {subscribeDto: %s}", publisher, dto));
    var event = new SubscribedChannelEvent(dto, publisher);
    applicationEventPublisher.publishEvent(event);
  }

  @Override
  public void unsubscribedChannel(SubscribeDto dto) {
    var publisher = PUBLISHER + "/unsubscribedChannel";
    log.info(String.format("[%s]: 구독(subscribe) 취소 완료 {subscribeDto: %s}", publisher, dto));
    var event = new UnsubscribedChannelEvent(dto, publisher);
    applicationEventPublisher.publishEvent(event);
  }
}
