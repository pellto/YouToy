package com.pellto.youtoy.domain.video.application;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.domain.video.domain.Video;
import com.pellto.youtoy.domain.video.dto.VideoDto;
import com.pellto.youtoy.domain.video.repository.VideoRepository;
import com.pellto.youtoy.domain.video.util.VideoFactory;
import java.util.ArrayList;
import java.util.Optional;
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
class VideoReadServiceTest {

  private static final String SERVICE_NAME = "VideoReadService";

  @InjectMocks
  private VideoReadService videoReadService;

  @Mock
  private VideoRepository videoRepository;

  @DisplayName("[" + SERVICE_NAME + ": findAll: success] 전체 조회 성공 테스트")
  @Test
  void findAllTest() {
    var video = VideoFactory.create();
    var videoList = new ArrayList<Video>();
    videoList.add(video);

    given(videoRepository.findAll()).willReturn(videoList);

    var foundVideoList = videoReadService.findAll();

    then(videoRepository).should(times(1)).findAll();
    Assertions.assertThat(foundVideoList).isNotEmpty();
    Assertions.assertThat(foundVideoList.size()).isEqualTo(1);
    Assertions.assertThat(foundVideoList.get(0).getClass()).isEqualTo(VideoDto.class);
  }

  @DisplayName("[" + SERVICE_NAME + ": findById: success] id 조건 조회 성공 테스트")
  @Test
  void findByIdSuccessTest() {
    var video = VideoFactory.create();

    given(videoRepository.findById(any())).willReturn(Optional.ofNullable(video));

    var foundVideo = videoReadService.findById(video.getId());

    then(videoRepository).should(times(1)).findById(video.getId());
    Assertions.assertThat(foundVideo).isNotNull();
    Assertions.assertThat(foundVideo.id()).isEqualTo(video.getId());
    Assertions.assertThat(foundVideo.getClass()).isEqualTo(VideoDto.class);
  }
}