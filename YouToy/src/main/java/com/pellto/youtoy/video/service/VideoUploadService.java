package com.pellto.youtoy.video.service;

import com.pellto.youtoy.global.dto.video.VideoDto;
import com.pellto.youtoy.global.dto.video.request.UploadVideoRequest;
import com.pellto.youtoy.video.domain.model.Video;
import com.pellto.youtoy.video.domain.model.VideoDetailInfo;
import com.pellto.youtoy.video.domain.port.in.VideoUploadPort;
import com.pellto.youtoy.video.domain.port.out.ChannelHandlePort;
import com.pellto.youtoy.video.domain.port.out.SaveVideoPort;
import com.pellto.youtoy.video.domain.port.out.VideoEventPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VideoUploadService implements VideoUploadPort {

  private final SaveVideoPort saveVideoPort;
  private final VideoEventPort videoEventPort;
  private final ChannelHandlePort channelHandlePort;

  @Override
  public VideoDto upload(UploadVideoRequest request) {
    if (!channelHandlePort.existByChannelId(request.channelId())) {
      throw new IllegalArgumentException("해당 채널 없음");
    }
    var videoDetailInfo = VideoDetailInfo.builder()
        .title(request.title())
        .description(request.description())
        .hashTags(request.hashTags())
        .build();
    var video = Video.builder()
        .channelId(request.channelId())
        .videoDetailInfo(videoDetailInfo)
        .build();

    video = saveVideoPort.save(video);

    var dto = video.toDto();
    videoEventPort.requestedVideoUpload(dto);
    return dto;
  }
}
