package com.pellto.youtoy.video.service;

import static org.mockito.BDDMockito.given;

import com.pellto.youtoy.video.domain.port.out.LoadVideoPort;
import com.pellto.youtoy.video.util.VideoFixtureFactory;
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
class VideoInfoServiceTest {

  private static final String SERVICE_NAME = "VideoInfoService";

  @InjectMocks
  private VideoInfoService videoInfoService;

  @Mock
  private LoadVideoPort loadVideoPort;


  @DisplayName("[" + SERVICE_NAME + "/getVideoInfo] getVideoInfo 성공 테스트")
  @Test
  void getVideoInfoSuccessTest() {
    var video = VideoFixtureFactory.create();

    given(loadVideoPort.load(video.getId())).willReturn(video);

    var gottenVideo = videoInfoService.getVideoInfo(video.getId());

    Assertions.assertThat(gottenVideo).isNotNull();
    Assertions.assertThat(gottenVideo).usingRecursiveComparison().isEqualTo(video.toDto());
  }

}