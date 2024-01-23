package com.pellto.youtoy.channel.domain.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.channel.domain.port.out.LoadChannelPort;
import com.pellto.youtoy.channel.util.ChannelFixtureFactory;
import com.pellto.youtoy.global.dto.channel.ChannelDto;
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
class ChannelReadServiceTest {

  private static final String SERVICE_NAME = "ChannelReadService";

  @InjectMocks
  private ChannelReadService channelReadService;
  @Mock
  private LoadChannelPort loadChannelPort;

  @DisplayName("[" + SERVICE_NAME + "/getChannelById] getChannelById 성공 테스트")
  @Test
  void getChannelByIdSuccessTest() {
    var channel = ChannelFixtureFactory.create();
    var channelDto = channel.toDto();

    given(loadChannelPort.load(channelDto.id())).willReturn(channel);

    var loadedChannel = channelReadService.getChannelById(channelDto.id());

    Assertions.assertThat(loadedChannel).isNotNull();
    Assertions.assertThat(loadedChannel.getClass()).isEqualTo(ChannelDto.class);
    Assertions.assertThat(loadedChannel).usingRecursiveComparison().isEqualTo(channelDto);
    then(loadChannelPort).should(times(1)).load(channelDto.id());
  }

}