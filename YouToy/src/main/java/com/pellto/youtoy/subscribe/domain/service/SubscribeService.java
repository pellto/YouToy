package com.pellto.youtoy.subscribe.domain.service;

import com.pellto.youtoy.global.dto.subscribe.SubscribeDto;
import com.pellto.youtoy.global.dto.subscribe.request.ChangeSubscribeLevelRequest;
import com.pellto.youtoy.global.dto.subscribe.request.SubscribeRequest;
import com.pellto.youtoy.global.dto.subscribe.request.UnsubscribeRequest;
import com.pellto.youtoy.subscribe.domain.model.Subscribe;
import com.pellto.youtoy.subscribe.domain.port.in.ChangeSubscribeLevelUsecase;
import com.pellto.youtoy.subscribe.domain.port.in.SubscribeUsecase;
import com.pellto.youtoy.subscribe.domain.port.in.UnsubscribeUsecase;
import com.pellto.youtoy.subscribe.domain.port.out.LoadSubscribePort;
import com.pellto.youtoy.subscribe.domain.port.out.SaveSubscribePort;
import com.pellto.youtoy.subscribe.domain.port.out.SubscribeEventPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubscribeService implements SubscribeUsecase, UnsubscribeUsecase,
    ChangeSubscribeLevelUsecase {

  private final LoadSubscribePort loadSubscribePort;
  private final SaveSubscribePort saveSubscribePort;
  private final SubscribeEventPort subscribeEventPort;

  @Override
  public void changeLevel(ChangeSubscribeLevelRequest request) {
    var subscribe = loadSubscribePort.loadBySubscriberIdAndSubscribedChannelId(
        request.subscriberId(), request.subscribedChannelId());

    subscribe.changeLevel(request.subscribeLevel());
    saveSubscribePort.save(subscribe);
  }

  @Override
  @Transactional
  public SubscribeDto subscribe(SubscribeRequest request) {
    var subscribe = Subscribe.builder()
        .subscriberId(request.subscriberId())
        .subscribedChannelId(request.subscribedChannelId())
        .build();

    subscribe = saveSubscribePort.save(subscribe);

    var dto = subscribe.toDto();
    subscribeEventPort.subscribedChannel(dto);
    return dto;
  }

  @Override
  @Transactional
  public void unsubscribe(UnsubscribeRequest request) {
    var subscribe = loadSubscribePort.loadBySubscriberIdAndSubscribedChannelId(
        request.subscriberId(), request.subscribedChannelId());

    saveSubscribePort.delete(subscribe);
    subscribeEventPort.unsubscribedChannel(subscribe.toDto());
  }
}
