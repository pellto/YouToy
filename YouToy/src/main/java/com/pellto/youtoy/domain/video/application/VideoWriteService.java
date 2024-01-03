package com.pellto.youtoy.domain.video.application;

import com.pellto.youtoy.domain.base.application.WriteService;
import com.pellto.youtoy.domain.video.domain.Video;
import com.pellto.youtoy.domain.video.dto.VideoDto;
import com.pellto.youtoy.domain.video.dto.VideoModifyRequest;
import com.pellto.youtoy.domain.video.dto.VideoUploadRequest;
import com.pellto.youtoy.domain.video.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VideoWriteService implements
    WriteService<VideoDto, VideoUploadRequest, VideoModifyRequest> {

  private final VideoRepository videoRepository;
  private final VideoReadService videoReadService;

  @Override
  public VideoDto write(VideoUploadRequest req) {
    var video = Video.builder()
        .channelId(req.channelId())
        .title(req.title())
        .description(req.description())
        .build();
    return videoReadService.toDto(videoRepository.save(video));
  }

  @Override
  public VideoDto modify(VideoModifyRequest modifyRequest) {
    return null;
  }

  @Override
  public void deleteById(Long id) {
    videoRepository.deleteById(id);
  }
}
