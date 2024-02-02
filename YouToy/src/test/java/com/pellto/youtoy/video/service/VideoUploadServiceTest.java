package com.pellto.youtoy.video.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.global.dto.video.VideoDto;
import com.pellto.youtoy.video.domain.port.out.ChannelHandlePort;
import com.pellto.youtoy.video.domain.port.out.SaveVideoPort;
import com.pellto.youtoy.video.domain.port.out.VideoEventPort;
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
class VideoUploadServiceTest {

  private static final String SERVICE_NAME = "VideoUploadService";

  @InjectMocks
  private VideoUploadService videoUploadService;

  @Mock
  private SaveVideoPort saveVideoPort;
  @Mock
  private VideoEventPort videoEventPort;
  @Mock
  private ChannelHandlePort channelHandlePort;

  @DisplayName("[" + SERVICE_NAME + "/upload] upload 성공 테스트")
  @Test
  void uploadSuccessTest() {
    var request = VideoFixtureFactory.createUploadVideoRequest();
    var video = VideoFixtureFactory.create();

    given(saveVideoPort.save(any())).willReturn(video);
    given(channelHandlePort.existByChannelId(video.getId())).willReturn(true);

    var uploadedVideo = videoUploadService.upload(request);

    Assertions.assertThat(uploadedVideo).isNotNull();
    Assertions.assertThat(uploadedVideo.id()).isNotNull();
    Assertions.assertThat(uploadedVideo.getClass()).isEqualTo(VideoDto.class);
    Assertions.assertThat(uploadedVideo).usingRecursiveComparison().isEqualTo(video.toDto());
    then(channelHandlePort).should(times(1)).existByChannelId(video.getId());
    then(saveVideoPort).should(times(1)).save(any());
    then(videoEventPort).should(times(1)).requestedVideoUpload(any());
  }

  @DisplayName("[" + SERVICE_NAME + "/upload] upload 실패 테스트")
  @Test
  void uploadFailWithNotExistChannelTest() {
    var request = VideoFixtureFactory.createUploadVideoRequest();

    given(channelHandlePort.existByChannelId(any())).willReturn(false);

    Assertions.assertThatThrownBy(() -> videoUploadService.upload(request))
        .isInstanceOf(IllegalArgumentException.class).hasMessage("해당 채널 없음");
    then(channelHandlePort).should(times(1)).existByChannelId(any());
    then(saveVideoPort).should(times(0)).save(any());
    then(videoEventPort).should(times(0)).requestedVideoUpload(any());
  }

}