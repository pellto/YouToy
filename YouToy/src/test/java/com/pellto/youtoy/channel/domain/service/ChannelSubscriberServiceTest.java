package com.pellto.youtoy.channel.domain.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.channel.domain.port.out.LoadChannelPort;
import com.pellto.youtoy.channel.domain.port.out.SaveChannelPort;
import com.pellto.youtoy.channel.util.ChannelFixtureFactory;
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
class ChannelSubscriberServiceTest {

  private static final String SERVICE_NAME = "ChannelSubscriberService";

  @InjectMocks
  private ChannelSubscriberService channelSubscriberService;
  @Mock
  private SaveChannelPort saveChannelPort;
  @Mock
  private LoadChannelPort loadChannelPort;

  @DisplayName("[" + SERVICE_NAME + "/increase] increase 성공 테스트")
  @Test
  void increaseSuccessTest() {
    var channel = ChannelFixtureFactory.create();

    given(loadChannelPort.load(channel.getId())).willReturn(channel);

    channelSubscriberService.increase(channel.getId());

    Assertions.assertThat(channel.getSubscriberCount()).isEqualTo(1L);
    then(loadChannelPort).should(times(1)).load(channel.getId());
    then(saveChannelPort).should(times(1)).save(any());
  }

  @DisplayName("[" + SERVICE_NAME + "/decrease] decrease 성공 테스트")
  @Test
  void decreaseSuccessTest() {
    var channel = ChannelFixtureFactory.createWithSubscriberCount(1L);

    given(loadChannelPort.load(channel.getId())).willReturn(channel);

    channelSubscriberService.decrease(channel.getId());

    Assertions.assertThat(channel.getSubscriberCount()).isEqualTo(0L);
    then(loadChannelPort).should(times(1)).load(channel.getId());
    then(saveChannelPort).should(times(1)).save(any());
  }
}