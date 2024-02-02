package com.pellto.youtoy.video.application.adapter.in.http;

import com.pellto.youtoy.global.dto.video.VideoDto;
import com.pellto.youtoy.global.dto.video.request.UploadVideoRequest;
import com.pellto.youtoy.video.domain.port.in.GetVideoInfosUsecase;
import com.pellto.youtoy.video.domain.port.in.VideoUploadPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
public class VideoController {

  private final VideoUploadPort videoUploadPort;
  private final GetVideoInfosUsecase getVideoInfosUsecase;

  @PostMapping
  public VideoDto upload(@RequestBody UploadVideoRequest request) {
    return videoUploadPort.upload(request);
  }

  @GetMapping("/{videoId}")
  public VideoDto getById(@PathVariable Long videoId) {
    return getVideoInfosUsecase.getVideoInfo(videoId);
  }

}
