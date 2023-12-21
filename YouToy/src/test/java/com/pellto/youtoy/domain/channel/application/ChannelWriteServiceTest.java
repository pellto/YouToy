package com.pellto.youtoy.domain.channel.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.domain.channel.dao.ChannelRepository;
import com.pellto.youtoy.domain.channel.util.ChannelUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@Tag("service")
@ExtendWith(MockitoExtension.class)
class ChannelWriteServiceTest {

  @InjectMocks
  private ChannelWriteService channelWriteService;
  @Mock
  private ChannelRepository channelRepository;
  @Mock
  private ChannelReadService channelReadService;

  @DisplayName("[channelWriteService: signUp: success] 채널 생성 성공 테스트")
  @Test
  void createSuccessTest() {
    var req = ChannelUtil.createChannelRequest();
    var channel = ChannelUtil.createChannel();
    var channelDto = ChannelUtil.createChannelDto();

    given(channelRepository.save(any())).willReturn(channel);
    given(channelReadService.toDto(channel)).willReturn(channelDto);

    var savedChannel = channelWriteService.create(req);

    then(channelRepository).should(times(1)).save(any());
    then(channelReadService).should(times(1)).toDto(channel);
    Assertions.assertNotNull(savedChannel);
    Assertions.assertEquals(channelDto, savedChannel);
  }
}