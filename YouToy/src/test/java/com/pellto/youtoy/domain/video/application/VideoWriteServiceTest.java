package com.pellto.youtoy.domain.video.application;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.domain.video.dto.VideoDto;
import com.pellto.youtoy.domain.video.repository.VideoRepository;
import com.pellto.youtoy.domain.video.util.VideoFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@Tag("service")
@ExtendWith(MockitoExtension.class)
class VideoWriteServiceTest {

  @InjectMocks
  private VideoWriteService videoWriteService;
  @Mock
  private VideoReadService videoReadService;
  @Mock
  private VideoRepository videoRepository;

  @DisplayName("[videoWriteService: write: success] 생성 성공 테스트")
  @Test
  void writeSuccessTest() {
    var req = VideoFactory.createUploadRequest();
    var video = VideoFactory.create(req);
    var videoDto = VideoFactory.createDto(video);

    given(videoRepository.save(any())).willReturn(video);
    given(videoReadService.toDto(video)).willReturn(videoDto);

    var uploadedVideo = videoWriteService.write(req);

    then(videoRepository).should(times(1)).save(any());
    then(videoReadService).should(times(1)).toDto(video);
    Assertions.assertThat(uploadedVideo).isNotNull();
    Assertions.assertThat(uploadedVideo.getClass()).isEqualTo(VideoDto.class);
    Assertions.assertThat(uploadedVideo.channelId()).isEqualTo(req.channelId());
    Assertions.assertThat(uploadedVideo.title()).isEqualTo(req.title());
    Assertions.assertThat(uploadedVideo.description()).isEqualTo(req.description());
  }

  @DisplayName("[videoWriteService: deleteById: success] 삭제 성공 테스트")
  @Test
  void deleteByIdSuccessTest() {
    var id = 1L;

    videoRepository.deleteById(id);

    then(videoRepository).should(times(1)).deleteById(id);
  }
}