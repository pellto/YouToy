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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscribeService implements SubscribeUsecase, UnsubscribeUsecase,
    ChangeSubscribeLevelUsecase {

  private final LoadSubscribePort loadSubscribePort;
  private final SaveSubscribePort saveSubscribePort;

  @Override
  public void changeLevel(ChangeSubscribeLevelRequest request) {
    var subscribe = loadSubscribePort.loadBySubscriberIdAndSubscribedChannelId(
        request.subscriberId(), request.subscribedChannelId());

    subscribe.changeLevel(request.subscribeLevel());
    saveSubscribePort.save(subscribe);
  }

  @Override
  public SubscribeDto subscribe(SubscribeRequest request) {
    System.out.println(11);
    var subscribe = Subscribe.builder()
        .subscriberId(request.subscriberId())
        .subscribedChannelId(request.subscribedChannelId())
        .build();

    System.out.println(22);

    subscribe = saveSubscribePort.save(subscribe);
    System.out.println(33);
    return subscribe.toDto();
  }

  @Override
  public void unsubscribe(UnsubscribeRequest request) {
    var subscribe = loadSubscribePort.loadBySubscriberIdAndSubscribedChannelId(
        request.subscriberId(), request.subscribedChannelId());

    saveSubscribePort.delete(subscribe);
  }
}
