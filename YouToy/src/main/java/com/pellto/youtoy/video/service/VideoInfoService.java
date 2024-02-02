package com.pellto.youtoy.video.service;

import com.pellto.youtoy.global.dto.video.VideoDto;
import com.pellto.youtoy.video.domain.port.in.GetVideoInfosUsecase;
import com.pellto.youtoy.video.domain.port.out.LoadVideoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VideoInfoService implements GetVideoInfosUsecase {

  private final LoadVideoPort loadVideoPort;

  @Override
  public VideoDto getVideoInfo(Long id) {
    var video = loadVideoPort.load(id);
    return video.toDto();
  }
}
