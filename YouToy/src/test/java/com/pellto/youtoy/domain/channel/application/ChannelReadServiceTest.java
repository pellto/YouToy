package com.pellto.youtoy.domain.channel.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.domain.channel.dao.ChannelRepository;
import com.pellto.youtoy.domain.channel.domain.Channel;
import com.pellto.youtoy.domain.channel.dto.ChannelDto;
import com.pellto.youtoy.domain.channel.util.ChannelUtil;
import java.util.ArrayList;
import java.util.Optional;
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
class ChannelReadServiceTest {

  @InjectMocks
  private ChannelReadService channelReadService;

  @Mock
  private ChannelRepository channelRepository;

  @DisplayName("[channelReadService: findAll: success] 채널 전체 조회 테스트")
  @Test
  void findAllSuccess() {
    var channel = ChannelUtil.createChannel();
    var channelList = new ArrayList<Channel>();
    channelList.add(channel);

    given(channelRepository.findAll()).willReturn(channelList);

    var foundChannels = channelReadService.findAll();

    then(channelRepository).should(times(1)).findAll();
    Assertions.assertNotNull(foundChannels);
    Assertions.assertEquals(1, foundChannels.size());
    Assertions.assertEquals(ChannelDto.class, foundChannels.get(0).getClass());
  }

  @DisplayName("[channelReadService: findById: success] id 조건 조회 테스트")
  @Test
  void findByIdSuccess() {
    var channelId = 1L;
    var channel = ChannelUtil.createChannel(channelId);

    given(channelRepository.findById(any())).willReturn(Optional.ofNullable(channel));

    var foundChannel = channelReadService.findById(channelId);

    then(channelRepository).should(times(1)).findById(channelId);
    Assertions.assertNotNull(foundChannel);
    Assertions.assertEquals(channel.getId(), foundChannel.id());
  }
}