package com.pellto.youtoy.domain.playlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.times;

import com.pellto.youtoy.domain.playlist.repository.PlaylistRepository;
import com.pellto.youtoy.domain.playlist.service.PlaylistWriteService;
import com.pellto.youtoy.util.error.ErrorCode;
import com.pellto.youtoy.util.playlist.CreatePlaylistCommandFixtureFactory;
import com.pellto.youtoy.util.playlist.PlaylistFixtureFactory;
import com.pellto.youtoy.util.playlist.UpdatePlaylistCommandFixtureFactory;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@Tag("domain")
@ExtendWith(MockitoExtension.class)
public class PlaylistServiceTest {

  private static final String DOMAIN = "Playlist";
  @InjectMocks
  private PlaylistWriteService playlistWriteService;

  @Mock
  private PlaylistRepository playlistRepository;

  @DisplayName("[" + DOMAIN + ": create: success] playlist 생성 성공 테스트")
  @Test
  public void createTest() {
    var cmd = CreatePlaylistCommandFixtureFactory.create();
    var playlist = PlaylistFixtureFactory.create();

    given(playlistRepository.save(any())).willReturn(playlist);

    var createdPlaylist = playlistWriteService.create(cmd);

    assertEquals(cmd.channelId(), createdPlaylist.getChannelId());
    assertEquals(cmd.title(), createdPlaylist.getTitle());
    assertEquals(cmd.targetRange(), createdPlaylist.getTargetRange());
    then(playlistRepository).should(times(1)).save(any());
  }

  @DisplayName("[" + DOMAIN + ": update: not entered id] command에 id 없을 경우 테스트")
  @Test
  public void updateNotEnteredIdTest() {
    var cmd = UpdatePlaylistCommandFixtureFactory.create(null);

    try {
      playlistWriteService.update(cmd);
    } catch (Exception e) {
      assertEquals(ErrorCode.NOT_ENTERED_PLAYLIST_ID.getMessage(), e.getMessage());
      then(playlistRepository).should(times(0)).findById(any());
      then(playlistRepository).should(times(0)).save(any());
    }
  }

  @DisplayName("[" + DOMAIN + ": update: not exist playlist] 존재하지 않는 playlist 변경 테스트")
  @Test
  public void updateNotExistTest() {
    var cmd = UpdatePlaylistCommandFixtureFactory.create(-1L);

    given(playlistRepository.findById(cmd.id())).willReturn(Optional.empty());

    try {
      playlistWriteService.update(cmd);
    } catch (Exception e) {
      then(playlistRepository).should(times(1)).findById(any());
      then(playlistRepository).should(times(0)).save(any());
      assertEquals(ErrorCode.NOT_EXIST_PLAYLIST.getMessage(), e.getMessage());
    }
  }

  @DisplayName("[" + DOMAIN + ": update: only targetRange success] playlist의 targetRange 변경 성공 테스트")
  @Test
  public void updateOnlyTargetRangeTest() {
    var targetRange = 1;
    var cmd = UpdatePlaylistCommandFixtureFactory.create(null, targetRange);
    var playlist = PlaylistFixtureFactory.create();
    var changedPlaylist = PlaylistFixtureFactory.create(cmd.targetRange());

    given(playlistRepository.save(any())).willReturn(changedPlaylist);
    given(playlistRepository.findById(any())).willReturn(Optional.ofNullable(playlist));

    playlistWriteService.update(cmd);

    assertNotNull(playlist);
    assertEquals(cmd.targetRange(), targetRange);
    assertEquals(targetRange, changedPlaylist.getTargetRange());
    then(playlistRepository).should(times(1)).findById(any());
    then(playlistRepository).should(times(1)).save(any());
  }

  @DisplayName("[" + DOMAIN + ": update: only title success] playlist의 title 변경 성공 테스트")
  @Test
  public void updateOnlyTitleTest() {
    var title = "Custom title";
    var cmd = UpdatePlaylistCommandFixtureFactory.create(title, null);
    var playlist = PlaylistFixtureFactory.create();
    var changedPlaylist = PlaylistFixtureFactory.create(cmd.title());

    given(playlistRepository.save(any())).willReturn(changedPlaylist);
    given(playlistRepository.findById(any())).willReturn(Optional.ofNullable(playlist));

    playlistWriteService.update(cmd);

    assertNotNull(playlist);
    assertEquals(cmd.title(), title);
    assertEquals(title, changedPlaylist.getTitle());
    then(playlistRepository).should(times(1)).findById(any());
    then(playlistRepository).should(times(1)).save(any());
  }

  @DisplayName("[" + DOMAIN + ": update: success] playlist 변경 성공 테스트")
  @Test
  public void updateTest() {
    var cmd = UpdatePlaylistCommandFixtureFactory.create();
    var playlist = PlaylistFixtureFactory.create();
    var changedPlaylist = PlaylistFixtureFactory.create(cmd.targetRange(), cmd.title());

    given(playlistRepository.save(any())).willReturn(changedPlaylist);
    given(playlistRepository.findById(any())).willReturn(Optional.ofNullable(playlist));

    playlistWriteService.update(cmd);

    assertNotNull(playlist);
    assertEquals(cmd.title(), changedPlaylist.getTitle());
    assertEquals(cmd.targetRange(), changedPlaylist.getTargetRange());
    then(playlistRepository).should(times(1)).findById(any());
    then(playlistRepository).should(times(1)).save(any());
  }
}
