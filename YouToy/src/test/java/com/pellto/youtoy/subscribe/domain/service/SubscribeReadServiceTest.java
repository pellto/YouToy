package com.pellto.youtoy.subscribe.domain.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.subscribe.domain.model.Subscribe;
import com.pellto.youtoy.subscribe.domain.port.out.LoadSubscribePort;
import com.pellto.youtoy.subscribe.util.SubscribeFixtureFactory;
import java.util.ArrayList;
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
class SubscribeReadServiceTest {

  private static final String SERVICE_NAME = "SubscribeReadService";

  @InjectMocks
  private SubscribeReadService subscribeReadService;

  @Mock
  private LoadSubscribePort loadSubscribePort;

  @DisplayName("[" + SERVICE_NAME + "/getChannels] getChannels 성공 테스트")
  @Test
  void getChannelsSuccessTest() {
    ArrayList<Subscribe> subscribes = new ArrayList<>();
    subscribes.add(SubscribeFixtureFactory.createWithSubscribedChannelId(1L));
    subscribes.add(SubscribeFixtureFactory.createWithSubscribedChannelId(2L));
    var subscriberId = subscribes.get(0).getSubscriberId();

    given(loadSubscribePort.loadsBySubscriberId(subscriberId)).willReturn(subscribes);

    var channelIds = subscribeReadService.getChannels(subscriberId);

    then(loadSubscribePort).should(times(1)).loadsBySubscriberId(subscriberId);
    Assertions.assertThat(channelIds.size()).isEqualTo(2);
    Assertions.assertThat(channelIds.get(0).getClass()).isEqualTo(Long.class);
  }
}