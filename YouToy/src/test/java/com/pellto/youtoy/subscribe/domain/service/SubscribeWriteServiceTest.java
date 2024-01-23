package com.pellto.youtoy.subscribe.domain.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.global.dto.subscribe.SubscribeDto;
import com.pellto.youtoy.subscribe.domain.model.SubscribedLevel;
import com.pellto.youtoy.subscribe.domain.port.out.LoadSubscribePort;
import com.pellto.youtoy.subscribe.domain.port.out.SaveSubscribePort;
import com.pellto.youtoy.subscribe.domain.port.out.SubscribeEventPort;
import com.pellto.youtoy.subscribe.util.SubscribeFixtureFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@Tag("service")
class SubscribeWriteServiceTest {

  private static final String SERVICE_NAME = "SubscribeWriteService";

  @InjectMocks
  private SubscribeWriteService subscribeWriteService;

  @Mock
  private LoadSubscribePort loadSubscribePort;
  @Mock
  private SaveSubscribePort saveSubscribePort;
  @Mock
  private SubscribeEventPort subscribeEventPort;


  @DisplayName("[" + SERVICE_NAME + "/subscribe] subscribe 성공 테스트")
  @Test
  void subscribeSuccessTest() {
    var request = SubscribeFixtureFactory.createSubscribeRequest();
    var subscribe = SubscribeFixtureFactory.create(request);

    given(saveSubscribePort.save(any())).willReturn(subscribe);

    var subscribed = subscribeWriteService.subscribe(request);

    Assertions.assertThat(subscribed).isNotNull();
    Assertions.assertThat(subscribed.getClass()).isEqualTo(SubscribeDto.class);
    Assertions.assertThat(subscribed.id()).isNotNull();
    Assertions.assertThat(subscribed.createdAt()).isNotNull();
    Assertions.assertThat(subscribed.subscribedLevel())
        .isEqualTo(SubscribedLevel.CUSTOM.getValue());
    then(saveSubscribePort).should(times(1)).save(any());
    then(subscribeEventPort).should(times(1)).subscribedChannel(any());
  }

  @DisplayName("[" + SERVICE_NAME + "/unsubscribe] unsubscribe 성공 테스트")
  @Test
  void unsubscribeSuccessTest() {
    var request = SubscribeFixtureFactory.createUnsubscribeRequest();
    var subscribe = SubscribeFixtureFactory.create(request);

    given(loadSubscribePort.loadBySubscriberIdAndSubscribedChannelId(subscribe.getSubscriberId(),
        subscribe.getSubscribedChannelId())).willReturn(subscribe);

    subscribeWriteService.unsubscribe(request);

    then(loadSubscribePort).should(times(1)).loadBySubscriberIdAndSubscribedChannelId(
        subscribe.getSubscriberId(), subscribe.getSubscribedChannelId());
    then(saveSubscribePort).should(times(1)).delete(any());
    then(subscribeEventPort).should(times(1)).unsubscribedChannel(any());
  }
}