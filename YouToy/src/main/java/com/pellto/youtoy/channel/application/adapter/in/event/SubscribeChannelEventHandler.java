package com.pellto.youtoy.channel.application.adapter.in.event;

import com.pellto.youtoy.channel.domain.port.in.IncrementSubscriberUsecase;
import com.pellto.youtoy.global.event.subscribe.SubscribedChannelEvent;
import com.pellto.youtoy.global.event.subscribe.UnsubscribedChannelEvent;
import com.pellto.youtoy.global.interfaces.InboundEventAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

@InboundEventAdapter
@RequiredArgsConstructor
@Slf4j
public class SubscribeChannelEventHandler {

  private static final String HANDLER = "SubscribeChannelEventHandler";

  private final IncrementSubscriberUsecase incrementSubscriberUsecase;

  @EventListener
  public void increase(SubscribedChannelEvent event) throws InterruptedException {
    var handler = HANDLER + "/increase";
    log.info(
        String.format("[%s] 구독자 수 증가 시작(by %s) {event: %s}", handler, event.getPublisher(), event));
    incrementSubscriberUsecase.increase(event.getSubscribeDto().subscribedChannelId());
    log.info(
        String.format("[%s] 구독자 수 증가 완료(by %s) {event: %s}", handler, event.getPublisher(), event));
  }

  @EventListener
  public void decrease(UnsubscribedChannelEvent event) throws InterruptedException {
    var handler = HANDLER + "/decrease";
    log.info(
        String.format("[%s] 구독자 수 감소 시작(by %s) {event: %s}", handler, event.getPublisher(), event));
    incrementSubscriberUsecase.decrease(event.getSubscribeDto().subscribedChannelId());
    log.info(
        String.format("[%s] 구독자 수 감소 완료(by %s) {event: %s}", handler, event.getPublisher(), event));
  }
}
