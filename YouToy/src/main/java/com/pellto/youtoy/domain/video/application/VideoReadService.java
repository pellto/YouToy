package com.pellto.youtoy.domain.video.application;

import com.pellto.youtoy.domain.base.application.ReadService;
import com.pellto.youtoy.domain.video.domain.Video;
import com.pellto.youtoy.domain.video.dto.VideoDto;
import com.pellto.youtoy.domain.video.repository.VideoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VideoReadService extends ReadService<Video, VideoDto> {

  private final VideoRepository videoRepository;

  @Override
  public List<VideoDto> findAll() {
    return videoRepository.findAll().stream().map(this::toDto).toList();
  }

  @Override
  public VideoDto findById(Long id) {
    return toDto(videoRepository.findById(id).orElseThrow());
  }

  @Override
  public Video getById(Long id) {
    return videoRepository.getReferenceById(id);
  }

  @Override
  public VideoDto toDto(Video entity) {
    return new VideoDto(entity.getId(), entity.getChannelId(), entity.getTitle(),
        entity.getDescription(), entity.getCommentCount(), entity.getLikeCount(),
        entity.getCreatedAt());
  }
}
