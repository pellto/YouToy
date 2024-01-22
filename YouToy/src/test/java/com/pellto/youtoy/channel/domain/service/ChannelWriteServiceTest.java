package com.pellto.youtoy.channel.domain.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.channel.domain.model.Channel;
import com.pellto.youtoy.channel.domain.port.out.ChannelEventPort;
import com.pellto.youtoy.channel.domain.port.out.LoadChannelPort;
import com.pellto.youtoy.channel.domain.port.out.SaveChannelPort;
import com.pellto.youtoy.channel.util.ChannelFixtureFactory;
import com.pellto.youtoy.global.dto.member.MemberDto;
import com.pellto.youtoy.global.dto.member.MemberInfoDto;
import java.time.LocalDateTime;
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
class ChannelWriteServiceTest {

  private static final String SERVICE_NAME = "ChannelWriteService";

  @InjectMocks
  private ChannelWriteService channelWriteService;
  @Mock
  private LoadChannelPort loadChannelPort;
  @Mock
  private SaveChannelPort saveChannelPort;
  @Mock
  private ChannelEventPort channelEventPort;

  @DisplayName("[" + SERVICE_NAME + "/create] create 성공 테스트")
  @Test
  void createSuccessTest() {
    var beforeSavedChannel = ChannelFixtureFactory.createBeforeSaved();
    var channel = ChannelFixtureFactory.create();

    given(saveChannelPort.save(any())).willReturn(channel);

    var createdChannel = channelWriteService.create(
        beforeSavedChannel.getOwnerId(),
        beforeSavedChannel.getChannelInfo().getDisplayName()
    );

    Assertions.assertThat(createdChannel).isNotNull();
    Assertions.assertThat(createdChannel.getClass()).isEqualTo(Channel.class);
    Assertions.assertThat(createdChannel).usingRecursiveComparison().isEqualTo(channel);
    then(saveChannelPort).should(times(1)).save(any());
  }

  @DisplayName("[" + SERVICE_NAME + "/delete] delete 성공 테스트")
  @Test
  void deleteSuccessTest() {
    var memberInfoDto = new MemberInfoDto("email", "pwd", "name", LocalDateTime.now());
    var memberDto = new MemberDto(1L, "uuid", LocalDateTime.now(), memberInfoDto, 1L);

    var channel = ChannelFixtureFactory.create();

    given(loadChannelPort.load(memberDto.id())).willReturn(channel);

    channelWriteService.delete(memberDto);

    then(loadChannelPort).should(times(1)).load(any());
    then(saveChannelPort).should(times(1)).delete(any());
    then(channelEventPort).should(times(1)).channelDeletedEvent(any());
  }
}