package com.pellto.youtoy.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.times;

import com.pellto.youtoy.domain.playlist.dto.PlaylistVideoDto;
import com.pellto.youtoy.domain.playlist.service.PlaylistVideoReadService;
import com.pellto.youtoy.domain.view.service.ShortReadService;
import com.pellto.youtoy.domain.view.service.VideoReadService;
import com.pellto.youtoy.util.playlist.PlaylistVideoDtoFixtureFactory;
import com.pellto.youtoy.util.types.VideoTypes;
import com.pellto.youtoy.util.view.ShortFixtureFactory;
import com.pellto.youtoy.util.view.VideoFixtureFactory;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@Tag("usecase")
@ExtendWith(MockitoExtension.class)
@DisplayName("[GetVideosInPlaylistUsecase Test]")
public class GetVideosInPlaylistUsecaseTest {

  private static final Long PLAYLIST_ID = 1L;
  @InjectMocks
  private GetVideosInPlaylistUsecase getVideosInPlaylistUsecase;
  @Mock
  private PlaylistVideoReadService playlistVideoReadService;
  @Mock
  private VideoReadService videoReadService;
  @Mock
  private ShortReadService shortReadService;

  @DisplayName("[execute: get shorts success] 플레이리스트에 속한 쇼츠 가져오기 성공 테스트")
  @Test
  public void executeShortTest() {
    var videoType = VideoTypes.SHORTS_TYPE.getValue();
    var playlistVideoDto = PlaylistVideoDtoFixtureFactory.create(videoType);
    var shortsDto = ShortFixtureFactory.toDto(ShortFixtureFactory.create());

    given(playlistVideoReadService.getByPlaylistId(PLAYLIST_ID))
        .willReturn(List.of(new PlaylistVideoDto[]{playlistVideoDto}));
    given(shortReadService.getShort(any())).willReturn(shortsDto);

    var videoContents = getVideosInPlaylistUsecase.execute(PLAYLIST_ID);

    assertEquals(1, videoContents.size());
    then(playlistVideoReadService).should(times(1)).getByPlaylistId(PLAYLIST_ID);
    then(videoReadService).should(times(0)).getVideo(any());
    then(shortReadService).should(times(1)).getShort(any());
  }

  @DisplayName("[execute: success] 플레이리스트에 속한 비디오 또는 쇼츠 가져오기 성공 테스트")
  @Test
  public void executeTest() {
    var playlistVideoVideoDto = PlaylistVideoDtoFixtureFactory.create(
        VideoTypes.VIDEO_TYPE.getValue()
    );
    var playlistVideoShortsDto = PlaylistVideoDtoFixtureFactory.create(
        VideoTypes.SHORTS_TYPE.getValue()
    );
    var shortsDto = ShortFixtureFactory.toDto(ShortFixtureFactory.create());
    var videoDto = VideoFixtureFactory.toDto(VideoFixtureFactory.create());

    given(playlistVideoReadService.getByPlaylistId(PLAYLIST_ID))
        .willReturn(List.of(new PlaylistVideoDto[]{playlistVideoVideoDto, playlistVideoShortsDto}));
    given(videoReadService.getVideo(any())).willReturn(videoDto);
    given(shortReadService.getShort(any())).willReturn(shortsDto);

    var videoContents = getVideosInPlaylistUsecase.execute(PLAYLIST_ID);

    assertEquals(2, videoContents.size());
    then(playlistVideoReadService).should(times(1)).getByPlaylistId(PLAYLIST_ID);
    then(videoReadService).should(times(1)).getVideo(any());
    then(shortReadService).should(times(1)).getShort(any());
  }

  @DisplayName("[execute: get video success] 플레이리스트에 속한 비디오 가져오기 성공 테스트")
  @Test
  public void executeVideoTest() {
    var videoType = VideoTypes.VIDEO_TYPE.getValue();
    var playlistVideoDto = PlaylistVideoDtoFixtureFactory.create(videoType);
    var videoDto = VideoFixtureFactory.toDto(VideoFixtureFactory.create());

    given(playlistVideoReadService.getByPlaylistId(PLAYLIST_ID))
        .willReturn(List.of(new PlaylistVideoDto[]{playlistVideoDto}));
    given(videoReadService.getVideo(any())).willReturn(videoDto);

    var videoContents = getVideosInPlaylistUsecase.execute(PLAYLIST_ID);

    assertEquals(1, videoContents.size());
    then(playlistVideoReadService).should(times(1)).getByPlaylistId(PLAYLIST_ID);
    then(videoReadService).should(times(1)).getVideo(any());
    then(shortReadService).should(times(0)).getShort(any());
  }
}
