package com.pellto.youtoy.domain.channel.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.domain.channel.domain.Subscribe;
import com.pellto.youtoy.domain.channel.dto.SubscribeDto;
import com.pellto.youtoy.domain.channel.repository.SubscribeRepository;
import com.pellto.youtoy.domain.channel.util.SubscribeFactory;
import com.pellto.youtoy.global.error.ErrorCode;
import java.util.ArrayList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@Tag("service")
@ExtendWith(MockitoExtension.class)
class SubscribeServiceTest {

  @InjectMocks
  private SubscribeService subscribeService;
  @Mock
  private SubscribeRepository subscribeRepository;
  @Mock
  private ChannelReadService channelReadService;

  @DisplayName("[subscribeService: findAll: success] 구독 전체 조회 테스트")
  @Test
  void findAllSuccess() {
    var subscribe = SubscribeFactory.createSubscribe();
    var subscribeList = new ArrayList<Subscribe>();
    subscribeList.add(subscribe);

    given(subscribeRepository.findAll()).willReturn(subscribeList);

    var foundSubscribes = subscribeService.findAll();

    then(subscribeRepository).should(times(1)).findAll();
    Assertions.assertThat(foundSubscribes).isNotNull();
    Assertions.assertThat(foundSubscribes.size()).isEqualTo(1);
    Assertions.assertThat(foundSubscribes.get(0).getClass()).isEqualTo(SubscribeDto.class);
  }

  @DisplayName("[subscribeService: subscribe: success] 구독 생성 성공 테스트")
  @Test
  void subscribeSuccessTest() {
    var req = SubscribeFactory.createSubscribeRelRequest();
    var subscribe = SubscribeFactory.createSubscribe();
    var subscribeDto = SubscribeFactory.createSubscribeDto();

    given(subscribeRepository.save(any())).willReturn(subscribe);
    given(channelReadService.existById(any())).willReturn(true);
    given(channelReadService.getById(req.subscriberId())).willReturn(subscribe.getSubscriber());
    given(channelReadService.getById(req.subscribedId())).willReturn(subscribe.getSubscribed());

    var savedSubscribe = subscribeService.subscribe(req);

    then(subscribeRepository).should(times(1)).save(any());
    then(channelReadService).should(times(2)).existById(any());
    then(channelReadService).should(times(2)).getById(any());
    Assertions.assertThat(savedSubscribe).isNotNull();
    Assertions.assertThat(subscribeDto).isEqualTo(savedSubscribe);
  }

  @DisplayName("[subscribeService: subscribe: NOT_EXIST_SUBSCRIBER_CHANNEL] 구독 생성 실패 테스트")
  @Test
  void subscribeFailTestByNotExistSubscriberChannel() {
    var req = SubscribeFactory.createSubscribeRelRequest();

    given(channelReadService.existById(req.subscriberId())).willReturn(false);

    try {
      subscribeService.subscribe(req);
    } catch (Exception e) {
      then(subscribeRepository).should(times(0)).save(any());
      then(channelReadService).should(times(1)).existById(any());
      then(channelReadService).should(times(0)).getById(any());
      Assertions.assertThat(e.getMessage())
          .isEqualTo(ErrorCode.NOT_EXIST_SUBSCRIBER_CHANNEL.getMessage());
    }
  }

  @DisplayName("[subscribeService: subscribe: NOT_EXIST_SUBSCRIBED_CHANNEL] 구독 생성 실패 테스트")
  @Test
  void subscribeFailTestByNotExistSubscribedChannel() {
    var req = SubscribeFactory.createSubscribeRelRequest();

    given(channelReadService.existById(req.subscriberId())).willReturn(true);
    given(channelReadService.existById(req.subscribedId())).willReturn(false);

    try {
      subscribeService.subscribe(req);
    } catch (Exception e) {
      then(subscribeRepository).should(times(0)).save(any());
      then(channelReadService).should(times(2)).existById(any());
      then(channelReadService).should(times(0)).getById(any());
      Assertions.assertThat(e.getMessage())
          .isEqualTo(ErrorCode.NOT_EXIST_SUBSCRIBED_CHANNEL.getMessage());
    }
  }

  @DisplayName("[subscribeService: unsubscribe: success] 구독 취소 성공 테스트")
  @Test
  void unsubscribeSuccessTest() {
    Long id = 1L;

    subscribeService.unsubscribe(id);

    then(subscribeRepository).should(times(1)).deleteById(id);
  }
}