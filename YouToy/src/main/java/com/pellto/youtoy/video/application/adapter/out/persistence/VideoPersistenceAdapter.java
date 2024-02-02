package com.pellto.youtoy.video.application.adapter.out.persistence;

import com.pellto.youtoy.global.interfaces.PersistenceAdapter;
import com.pellto.youtoy.video.domain.model.Video;
import com.pellto.youtoy.video.domain.port.out.LoadVideoPort;
import com.pellto.youtoy.video.domain.port.out.SaveVideoPort;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class VideoPersistenceAdapter implements LoadVideoPort, SaveVideoPort {

  private final VideoMapper videoMapper;
  private final VideoJpaDataRepository jpaDataRepository;

  @Override
  public Video load(Long videoId) {
    var entity = jpaDataRepository.findById(videoId)
        .orElseThrow(() -> new IllegalArgumentException("비디오 없음"));
    return videoMapper.toDomain(entity);
  }

  @Override
  public Video save(Video video) {
    var entity = videoMapper.toEntity(video);
    entity = jpaDataRepository.save(entity);
    return videoMapper.toDomain(entity);
  }
}
