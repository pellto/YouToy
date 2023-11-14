package com.pellto.youtoy.domain.video;

import com.pellto.youtoy.domain.video.repository.VideoRepository;
import com.pellto.youtoy.domain.video.service.VideoWriteService;
import com.pellto.youtoy.util.error.ErrorCode;
import com.pellto.youtoy.util.video.UpdateVideoCommandFixtureFactory;
import com.pellto.youtoy.util.video.UploadVideoCommandFixtureFactory;
import com.pellto.youtoy.util.video.VideoFixtureFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class VideoServiceTest {
    @InjectMocks
    private VideoWriteService videoWriteService;
    @Mock
    private VideoRepository videoRepository;

    @DisplayName("[Video: upload: success] 비디오 업로드 성공 테스트")
    @Test
    // TODO: real upload video test
    public void uploadVideoTest() {
        // given
        var cmd = UploadVideoCommandFixtureFactory.create();
        var video = VideoFixtureFactory.create(cmd);

        // mocking
        given(videoRepository.save(any())).willReturn(video);

        // when
        var uploadedVideo = videoWriteService.upload(cmd);

        assertEquals(cmd.channelId(), uploadedVideo.getChannelId());
        assertEquals(cmd.title(), uploadedVideo.getTitle());
        assertEquals(cmd.description(), uploadedVideo.getDescription());
        then(videoRepository).should(times(1)).save(any());
    }

    @DisplayName("[Video: update: success] 비디오 정보 업데이트 성공 테스트")
    @Test
    public void updateVideoTest() {
        // given
        var video = VideoFixtureFactory.create();
        var cmd = UpdateVideoCommandFixtureFactory.create();
        var changedVideo = VideoFixtureFactory.create(cmd);

        // mocking
        given(videoRepository.findById(any())).willReturn(Optional.ofNullable(video));
        given(videoRepository.save(any())).willReturn(changedVideo);

        // when
        var updatedVideo = videoWriteService.update(cmd);

        // then
        assertNotNull(updatedVideo);
        assertNotNull(updatedVideo.getId());
        assertEquals(cmd.title(), updatedVideo.getTitle());
        assertEquals(cmd.description(), updatedVideo.getDescription());
        then(videoRepository).should(times(1)).findById(any());
        then(videoRepository).should(times(1)).save(any());
    }

    @DisplayName("[Video: update: not exist video] 비디오 정보 업데이트시 없는 video id 테스트")
    @Test
    public void updateVideoNotExistVideoTest() {
        // given
        var cmd = UpdateVideoCommandFixtureFactory.create();

        // mocking
        given(videoRepository.findById(any())).willReturn(null);
        try {
            // when
            videoWriteService.update(cmd);
        } catch (Exception e) {
            // then
            assertEquals(e.getClass(), NullPointerException.class);
            then(videoRepository).should(times(1)).findById(any());
            then(videoRepository).should(times(0)).save(any());
        }
    }

    @DisplayName("[Video: remove: success] 비디오 삭제 성공 테스트")
    @Test
    public void removeVideoTest() {
        Long id = 1L;

        given(videoRepository.existVideo(any())).willReturn(true);

        videoWriteService.remove(id);

        then(videoRepository).should(times(1)).existVideo(any());
        then(videoRepository).should(times(1)).delete(any());
    }

    @DisplayName("[Video: remove: not exist video] 비디오 삭제 시 없는 비디오 테스트")
    @Test
    public void removeNotExistVideoTest() {
        Long id = 1L;

        given(videoRepository.existVideo(any())).willReturn(false);

        try {
            videoWriteService.remove(id);
        } catch (Exception e) {
            assertEquals(ErrorCode.NOT_EXIST_VIDEO.getMessage(), e.getMessage());
            then(videoRepository).should(times(1)).existVideo(any());
            then(videoRepository).should(times(0)).delete(any());
        }
    }

    @DisplayName("[Video: incrementViewCount: success] 비디오 view count 증가 성공 테스트")
    @Test
    public void incrementViewCountTest() {
        Long id = 1L;
        var video = VideoFixtureFactory.create();

        given(videoRepository.findById(any())).willReturn(Optional.ofNullable(video));

        videoWriteService.incrementViewCount(id);

        assertNotNull(video);
        assertEquals(video.getViewCount(), 1L);
        then(videoRepository).should(times(1)).findById(any());
        then(videoRepository).should(times(1)).save(any());
    }

    @DisplayName("[Video: incrementViewCount: not exist video] 비디오 view count 증가 시 없는 비디오 테스트")
    @Test
    public void incrementViewCountNotExistVideTest() {
        Long id = 1L;

        given(videoRepository.findById(any())).willReturn(null);

        try {
            videoWriteService.incrementViewCount(id);
        } catch (Exception e) {
            assertEquals(NullPointerException.class, e.getClass());
            then(videoRepository).should(times(1)).findById(any());
            then(videoRepository).should(times(0)).save(any());
        }
    }
}
