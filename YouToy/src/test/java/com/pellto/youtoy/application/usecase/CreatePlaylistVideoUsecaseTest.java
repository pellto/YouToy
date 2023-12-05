package com.pellto.youtoy.application.usecase;


import com.pellto.youtoy.domain.playlist.service.PlaylistReadService;
import com.pellto.youtoy.domain.playlist.service.PlaylistVideoWriteService;
import com.pellto.youtoy.domain.view.service.ShortReadService;
import com.pellto.youtoy.domain.view.service.VideoReadService;
import com.pellto.youtoy.util.error.ErrorCode;
import com.pellto.youtoy.util.playlist.CreatePlaylistVideoCommandFixtureFactory;
import com.pellto.youtoy.util.playlist.PlaylistVideoFixtureFactory;
import com.pellto.youtoy.util.types.VideoTypes;
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
@DisplayName("[CreatePlaylistVideoUsecase Test]")
public class CreatePlaylistVideoUsecaseTest {
    @InjectMocks
    private CreatePlaylistVideoUsecase createPlaylistVideoUsecase;
    @Mock
    private PlaylistVideoWriteService playlistVideoWriteService;
    @Mock
    private PlaylistReadService playlistReadService;
    @Mock
    private VideoReadService videoReadService;
    @Mock
    private ShortReadService shortReadService;

    @DisplayName("[video: success] 플레이리스트에 비디오 추가 성공 테스트")
    @Test
    public void executeVideoTest() {
        var videoType = VideoTypes.VIDEO_TYPE.getValue();
        var cmd = CreatePlaylistVideoCommandFixtureFactory.create(videoType);
        var playlistVideDto = PlaylistVideoFixtureFactory.toDto(
                PlaylistVideoFixtureFactory.create(videoType)
        );

        given(videoReadService.existVideo(cmd.videoId())).willReturn(true);
        given(playlistReadService.existById(cmd.playlistId())).willReturn(true);
        given(playlistVideoWriteService.create(cmd)).willReturn(playlistVideDto);

        var createdPlaylistVideDto = createPlaylistVideoUsecase.execute(cmd);

        assertEquals(cmd.playlistId(), createdPlaylistVideDto.playlistId());
        assertEquals(cmd.videoId(), createdPlaylistVideDto.videoId());
        assertEquals(cmd.videoType(), createdPlaylistVideDto.videoType());
        then(videoReadService).should(times(1)).existVideo(cmd.videoId());
        then(shortReadService).should(times(0)).existShort(any());
        then(playlistReadService).should(times(1)).existById(cmd.playlistId());
        then(playlistVideoWriteService).should(times(1)).create(cmd);
    }

    @DisplayName("[video: not exist video] 플레이리스트에 존재하지 않는 비디오 추가 실패 테스트")
    @Test
    public void executeNotExistVideoTest() {
        var videoType = VideoTypes.VIDEO_TYPE.getValue();
        var cmd = CreatePlaylistVideoCommandFixtureFactory.create(videoType);

        given(videoReadService.existVideo(cmd.videoId())).willReturn(false);

        try {
            createPlaylistVideoUsecase.execute(cmd);
        } catch (Exception e) {
            assertEquals(ErrorCode.NOT_EXIST_VIDEO.getMessage(), e.getMessage());
            then(videoReadService).should(times(1)).existVideo(cmd.videoId());
            then(shortReadService).should(times(0)).existShort(any());
            then(playlistReadService).should(times(0)).existById(any());
            then(playlistVideoWriteService).should(times(0)).create(any());
        }
    }

    @DisplayName("[video: not exist playlist] 존재하지 않는 플레이리스트에 비디오 추가 실패 테스트")
    @Test
    public void executeVideoNotExistPlaylistTest() {
        var videoType = VideoTypes.VIDEO_TYPE.getValue();
        var cmd = CreatePlaylistVideoCommandFixtureFactory.create(videoType);

        given(videoReadService.existVideo(cmd.videoId())).willReturn(true);
        given(playlistReadService.existById(cmd.playlistId())).willReturn(false);

        try {
            createPlaylistVideoUsecase.execute(cmd);
        } catch (Exception e) {
            assertEquals(ErrorCode.NOT_EXIST_PLAYLIST.getMessage(), e.getMessage());
            then(videoReadService).should(times(1)).existVideo(cmd.videoId());
            then(shortReadService).should(times(0)).existShort(any());
            then(playlistReadService).should(times(1)).existById(any());
            then(playlistVideoWriteService).should(times(0)).create(any());
        }
    }

    @DisplayName("[short: success] 플레이리스트에 쇼츠 추가 성공 테스트")
    @Test
    public void executeShortTest() {
        var videoType = VideoTypes.SHORTS_TYPE.getValue();
        var cmd = CreatePlaylistVideoCommandFixtureFactory.create(videoType);
        var playlistVideDto = PlaylistVideoFixtureFactory.toDto(
                PlaylistVideoFixtureFactory.create(videoType)
        );

        given(shortReadService.existShort(cmd.videoId())).willReturn(true);
        given(playlistReadService.existById(cmd.playlistId())).willReturn(true);
        given(playlistVideoWriteService.create(cmd)).willReturn(playlistVideDto);

        var createdPlaylistVideDto = createPlaylistVideoUsecase.execute(cmd);

        assertEquals(cmd.playlistId(), createdPlaylistVideDto.playlistId());
        assertEquals(cmd.videoId(), createdPlaylistVideDto.videoId());
        assertEquals(cmd.videoType(), createdPlaylistVideDto.videoType());
        then(videoReadService).should(times(0)).existVideo(any());
        then(shortReadService).should(times(1)).existShort(cmd.videoId());
        then(playlistReadService).should(times(1)).existById(cmd.playlistId());
        then(playlistVideoWriteService).should(times(1)).create(cmd);
    }

    @DisplayName("[short: not exist short] 플레이리스트에 존재하지 않는 쇼츠 추가 실패 테스트")
    @Test
    public void executeNotExistShortTest() {
        var videoType = VideoTypes.SHORTS_TYPE.getValue();
        var cmd = CreatePlaylistVideoCommandFixtureFactory.create(videoType);

        given(shortReadService.existShort(cmd.videoId())).willReturn(false);

        try {
            createPlaylistVideoUsecase.execute(cmd);
        } catch (Exception e) {
            assertEquals(ErrorCode.NOT_EXIST_SHORT.getMessage(), e.getMessage());
            then(videoReadService).should(times(0)).existVideo(any());
            then(shortReadService).should(times(1)).existShort(cmd.videoId());
            then(playlistReadService).should(times(0)).existById(any());
            then(playlistVideoWriteService).should(times(0)).create(any());
        }
    }

    @DisplayName("[short: not exist playlist] 존재하지 않는 플레이리스트에 쇼츠 추가 실패 테스트")
    @Test
    public void executeShortNotExistPlaylistTest() {
        var videoType = VideoTypes.SHORTS_TYPE.getValue();
        var cmd = CreatePlaylistVideoCommandFixtureFactory.create(videoType);

        given(shortReadService.existShort(cmd.videoId())).willReturn(true);
        given(playlistReadService.existById(cmd.playlistId())).willReturn(false);

        try {
            createPlaylistVideoUsecase.execute(cmd);
        } catch (Exception e) {
            assertEquals(ErrorCode.NOT_EXIST_PLAYLIST.getMessage(), e.getMessage());
            then(videoReadService).should(times(0)).existVideo(any());
            then(shortReadService).should(times(1)).existShort(cmd.videoId());
            then(playlistReadService).should(times(1)).existById(any());
            then(playlistVideoWriteService).should(times(0)).create(any());
        }
    }

    @DisplayName("[----: unsupported video type] 플레이리스트에 지원하지 않는 타입 추가 실패 테스트")
    @Test
    public void executeUnsupportedVideoTypeTest() {
        var videoType = -1;
        var cmd = CreatePlaylistVideoCommandFixtureFactory.create(videoType);

        try {
            createPlaylistVideoUsecase.execute(cmd);
        } catch (Exception e) {
            assertEquals(ErrorCode.UNSUPPORTED_VIDEO_TYPE.getMessage(), e.getMessage());
            then(videoReadService).should(times(0)).existVideo(any());
            then(shortReadService).should(times(0)).existShort(cmd.videoId());
            then(playlistReadService).should(times(0)).existById(any());
            then(playlistVideoWriteService).should(times(0)).create(any());
        }
    }
}
