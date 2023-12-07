package com.pellto.youtoy.application.usecase;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.times;

import com.pellto.youtoy.domain.channel.service.ChannelReadService;
import com.pellto.youtoy.domain.subscribe.service.SubscribeWriteService;
import com.pellto.youtoy.domain.user.service.UserReadService;
import com.pellto.youtoy.util.channel.ChannelFixtureFactory;
import com.pellto.youtoy.util.user.UserFixtureFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@Tag("usecase")
@ExtendWith(MockitoExtension.class)
@DisplayName("[CancelChannelSubscribeUsecase Test]")
public class CancelChannelSubscribeUsecaseTest {

  private static final Long USER_ID = 1L;
  private static final Long CHANNEL_ID = 1L;
  @InjectMocks
  private CancelChannelSubscribeUsecase cancelChannelSubscribeUsecase;
  @Mock
  private UserReadService userReadService;
  @Mock
  private ChannelReadService channelReadService;
  @Mock
  private SubscribeWriteService subscribeWriteService;

  @DisplayName("[execute: success] 성공 테스트")
  @Test
  public void executeTest() {
    var userDto = UserFixtureFactory.toDto(UserFixtureFactory.create());
    var channelDto = ChannelFixtureFactory.toDto(ChannelFixtureFactory.create());

    given(userReadService.getUser(any())).willReturn(userDto);
    given(channelReadService.getChannel(any())).willReturn(channelDto);

    cancelChannelSubscribeUsecase.execute(USER_ID, CHANNEL_ID);

    then(userReadService).should(times(1)).getUser(any());
    then(channelReadService).should(times(1)).getChannel(any());
    then(subscribeWriteService).should(times(1)).delete(any(), any());
  }
}
