package com.pellto.youtoy.domain.playlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.times;

import com.pellto.youtoy.domain.playlist.repository.PlaylistVideoRepository;
import com.pellto.youtoy.domain.playlist.service.PlaylistVideoReadService;
import com.pellto.youtoy.domain.playlist.service.PlaylistVideoWriteService;
import com.pellto.youtoy.util.error.ErrorCode;
import com.pellto.youtoy.util.playlist.CreatePlaylistVideoCommandFixtureFactory;
import com.pellto.youtoy.util.playlist.PlaylistVideoDtoFixtureFactory;
import com.pellto.youtoy.util.playlist.PlaylistVideoFixtureFactory;
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
public class PlaylistVideoServiceTest {

  private static final String DOMAIN = "Playlist-Video";
  @InjectMocks
  private PlaylistVideoWriteService playlistVideoWriteService;
  @Mock
  private PlaylistVideoReadService playlistVideoReadService;
  @Mock
  private PlaylistVideoRepository playlistVideoRepository;

  @DisplayName("[" + DOMAIN + ": create: success] playlist-video 생성 성공 테스트")
  @Test
  public void createTest() {
    var cmd = CreatePlaylistVideoCommandFixtureFactory.create();
    var playlistVideo = PlaylistVideoFixtureFactory.create();
    var playlistVideoDto = PlaylistVideoDtoFixtureFactory.create();

    given(playlistVideoRepository.save(any())).willReturn(playlistVideo);
    given(playlistVideoReadService.toDto(any())).willReturn(playlistVideoDto);

    var createdPlaylistVideo = playlistVideoWriteService.create(cmd);

    assertEquals(cmd.playlistId(), createdPlaylistVideo.playlistId());
    assertEquals(cmd.videoId(), createdPlaylistVideo.videoId());
    assertEquals(cmd.videoType(), createdPlaylistVideo.videoType());
    then(playlistVideoReadService).should(times(1)).toDto(any());
    then(playlistVideoRepository).should(times(1)).save(any());
  }

  @DisplayName("[" + DOMAIN + ": delete: not exist playlist-video] 없는 playlist-video 삭제 실패 테스트")
  @Test
  public void deleteNotExistPlaylistVideoTest() {
    Long id = 1L;

    given(playlistVideoRepository.findById(any())).willReturn(Optional.empty());

    try {
      playlistVideoWriteService.delete(id);
    } catch (Exception e) {
      assertEquals(ErrorCode.NOT_EXIST_PLAYLIST_VIDEO.getMessage(), e.getMessage());
      then(playlistVideoRepository).should(times(1)).findById(any());
      then(playlistVideoRepository).should(times(0)).delete(any());
    }
  }

  @DisplayName("[" + DOMAIN + ": delete: success] playlist-video 삭제 성공 테스트")
  @Test
  public void deleteTest() {
    Long id = 1L;
    var playlistVideo = PlaylistVideoFixtureFactory.create();

    given(playlistVideoRepository.findById(any())).willReturn(Optional.ofNullable(playlistVideo));

    playlistVideoWriteService.delete(id);

    then(playlistVideoRepository).should(times(1)).findById(any());
    then(playlistVideoRepository).should(times(1)).delete(any());
  }
}
