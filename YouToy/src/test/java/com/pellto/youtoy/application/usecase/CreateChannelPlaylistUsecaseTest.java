package com.pellto.youtoy.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.times;

import com.pellto.youtoy.domain.channel.service.ChannelReadService;
import com.pellto.youtoy.domain.playlist.service.PlaylistReadService;
import com.pellto.youtoy.domain.playlist.service.PlaylistWriteService;
import com.pellto.youtoy.util.error.ErrorCode;
import com.pellto.youtoy.util.playlist.CreatePlaylistCommandFixtureFactory;
import com.pellto.youtoy.util.playlist.PlaylistFixtureFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@Tag("usecase")
@ExtendWith(MockitoExtension.class)
@DisplayName("[CreateChannelPlaylistUsecase Test]")
public class CreateChannelPlaylistUsecaseTest {

  @InjectMocks
  private CreateChannelPlaylistUsecase createChannelPlaylistUsecase;
  @Mock
  private PlaylistWriteService playlistWriteService;
  @Mock
  private PlaylistReadService playlistReadService;
  @Mock
  private ChannelReadService channelReadService;

  @DisplayName("[execute: not exist channel] 존재하지 않는 채널에 playlist 생성 실패 테스트")
  @Test
  public void executeNotExistChannelTest() {
    var cmd = CreatePlaylistCommandFixtureFactory.create();

    given(channelReadService.isExist(any())).willReturn(false);

    try {
      createChannelPlaylistUsecase.execute(cmd);
    } catch (Exception e) {
      assertEquals(ErrorCode.NOT_EXIST_CHANNEL.getMessage(), e.getMessage());
      then(channelReadService).should(times(1)).isExist(any());
      then(playlistWriteService).should(times(0)).create(any());
      then(playlistReadService).should(times(0)).toDto(any());
    }
  }

  @DisplayName("[execute: success] playlist 생성 성공 테스트")
  @Test
  public void executeTest() {
    var cmd = CreatePlaylistCommandFixtureFactory.create();
    var playlist = PlaylistFixtureFactory.create();
    var playlistDto = PlaylistFixtureFactory.toDto(playlist);

    given(channelReadService.isExist(any())).willReturn(true);
    given(playlistWriteService.create(any())).willReturn(playlist);
    given(playlistReadService.toDto(any())).willReturn(playlistDto);

    var createdPlaylist = createChannelPlaylistUsecase.execute(cmd);

    assertEquals(cmd.channelId(), createdPlaylist.channelId());
    assertEquals(cmd.targetRange(), createdPlaylist.targetRange());
    assertEquals(cmd.title(), createdPlaylist.title());
    then(channelReadService).should(times(1)).isExist(any());
    then(playlistWriteService).should(times(1)).create(any());
    then(playlistReadService).should(times(1)).toDto(any());
  }
}
