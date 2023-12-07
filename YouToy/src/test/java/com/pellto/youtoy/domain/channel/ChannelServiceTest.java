package com.pellto.youtoy.domain.channel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.reset;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.times;

import com.pellto.youtoy.domain.channel.repository.ChannelRepository;
import com.pellto.youtoy.domain.channel.service.ChannelWriteService;
import com.pellto.youtoy.util.channel.ChannelFixtureFactory;
import com.pellto.youtoy.util.channel.CreateChannelCommandFixtureFactory;
import com.pellto.youtoy.util.channel.UpdateChannelCommandFixtureFactory;
import com.pellto.youtoy.util.error.ErrorCode;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@Tag("domain")
@ExtendWith(MockitoExtension.class)
public class ChannelServiceTest {

  @InjectMocks
  private ChannelWriteService channelWriteService;

  @Mock
  private ChannelRepository channelRepository;

  @BeforeEach
  public void clearMocking() {
    reset(channelRepository);
  }

  @DisplayName("[Channel: create: displayName is required] 채널 생성시 display name 필수 테스트")
  @Test
  public void createChannelDisplayNameIsRequired() {
    // given
    var cmd = CreateChannelCommandFixtureFactory.createWithDisplayName("");

    try {
      // when
      channelWriteService.create(cmd);
    } catch (Exception e) {
      // then
      assertEquals(e.getMessage(), ErrorCode.CHANNEL_DISPLAY_NAME_IS_REQUIRED.getMessage());
    }
    then(channelRepository).should(times(0)).save(any());
  }

  @DisplayName("[Channel: create: ownerId is required] 채널 생성시 owner id 필수 테스트")
  @Test
  public void createChannelOwnerIdIsRequired() {
    // given
    var cmd = CreateChannelCommandFixtureFactory.createWithOwnerId(null);

    try {
      // when
      channelWriteService.create(cmd);
    } catch (Exception e) {
      // then
      assertEquals(e.getMessage(), ErrorCode.OWNER_ID_IS_REQUIRED.getMessage());
    }
    then(channelRepository).should(times(0)).save(any());
  }

  @DisplayName("[Channel: create] 채널 생성 테스트")
  @Test
  public void createChannelTest() {
    // given
    var cmd = CreateChannelCommandFixtureFactory.create();
    var channel = ChannelFixtureFactory.create(cmd);

    // mocking
    given(channelRepository.save(any())).willReturn(channel);

    // when
    var retChannel = channelWriteService.create(cmd);

    assertEquals(cmd.ownerId(), retChannel.getOwnerId());
    assertEquals(cmd.displayName(), retChannel.getDisplayName());
    then(channelRepository).should(times(1)).save(any());
  }

  @DisplayName("[Channel: update: already exist handle] 채널 정보 변경시 이미 존재하는 handle 테스트")
  @Test
  public void updateChannelAlreadyExistHandleTest() {
    // given
    var channel = ChannelFixtureFactory.create(CreateChannelCommandFixtureFactory.create());
    var cmd = UpdateChannelCommandFixtureFactory.getWithHandle("alreadyExistHandle");

    // mocking
    given(channelRepository.findById(any())).willReturn(Optional.ofNullable(channel));
    given(channelRepository.existsHandle(any())).willReturn(true);

    try {
      // when
      channelWriteService.update(cmd);
    } catch (Exception e) {
      // then
      assertEquals(e.getMessage(), ErrorCode.ALREADY_EXIST_HANDLE.getMessage());
    }
    then(channelRepository).should(times(1)).findById(any());
    then(channelRepository).should(times(1)).existsHandle(any());
    then(channelRepository).should(times(0)).save(any());
  }

  @DisplayName("[Channel: update: .save() not called] 채널 정보 변경시 변경 없을 경우 테스트")
  @Test
  public void updateChannelSaveNotCalledTest() {
    // given
    var channel = ChannelFixtureFactory.create(CreateChannelCommandFixtureFactory.create());
    var cmd = UpdateChannelCommandFixtureFactory.get(null, null, null, null, null, null);

    // mocking
    given(channelRepository.findById(any())).willReturn(Optional.ofNullable(channel));

    // when
    channelWriteService.update(cmd);

    // then
    then(channelRepository).should(times(1)).findById(any());
    then(channelRepository).should(times(0)).existsHandle(any());
    then(channelRepository).should(times(0)).save(any());
  }

  @DisplayName("[Channel: update: success] 채널 정보 변경 테스트")
  @Test
  public void updateChannelTest() {
    // given
    var channel = ChannelFixtureFactory.create(
        CreateChannelCommandFixtureFactory.create()
    );
    var cmd = UpdateChannelCommandFixtureFactory.get();

    // mocking
    given(channelRepository.findById(any())).willReturn(Optional.ofNullable(channel));
    given(channelRepository.existsHandle(any())).willReturn(false);
    given(channelRepository.save(any())).willReturn(channel);

    // when
    var returnChannel = channelWriteService.update(cmd);

    // then
    then(channelRepository).should(times(1)).findById(any());
    then(channelRepository).should(times(1)).existsHandle(any());
    then(channelRepository).should(times(1)).save(any());
    assertEquals(cmd.handle(), returnChannel.getHandle());
    assertEquals(cmd.displayName(), returnChannel.getDisplayName());
    assertEquals(cmd.description(), returnChannel.getDescription());
    assertEquals(cmd.banner(), returnChannel.getBanner());
    assertEquals(cmd.profile(), returnChannel.getProfile());
    assertEquals(cmd.id(), returnChannel.getId());
  }
}
