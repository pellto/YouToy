package com.pellto.youtoy.application.usecase;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.*;

@Tag("usecase")
@ExtendWith(MockitoExtension.class)
@DisplayName("[CancelChannelSubscribeUsecase Test]")
public class CancelChannelSubscribeUsecaseTest {
    @InjectMocks
    private CancelChannelSubscribeUsecase cancelChannelSubscribeUsecase;
    @Mock
    private UserReadService userReadService;
    @Mock
    private ChannelReadService channelReadService;
    @Mock
    private SubscribeWriteService subscribeWriteService;

    private static final Long USER_ID = 1L;
    private static final Long CHANNEL_ID = 1L;

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
