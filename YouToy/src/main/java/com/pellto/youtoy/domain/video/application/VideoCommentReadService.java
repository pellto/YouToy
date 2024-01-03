package com.pellto.youtoy.domain.video.application;

import com.pellto.youtoy.domain.base.application.ReadService;
import com.pellto.youtoy.domain.video.domain.VideoComment;
import com.pellto.youtoy.domain.video.dto.VideoCommentDto;
import com.pellto.youtoy.domain.video.repository.VideoCommentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class VideoCommentReadService implements ReadService<VideoComment, VideoCommentDto> {

  private final VideoCommentRepository videoCommentRepository;

  @Override
  public List<VideoCommentDto> findAll() {
    return videoCommentRepository.findAll().stream().map(this::toDto).toList();
  }

  @Override
  public VideoCommentDto findById(Long id) {
    var comment = videoCommentRepository.findById(id).orElseThrow();
    return toDto(comment);
  }

  @Override
  public VideoComment getById(Long id) {
    return videoCommentRepository.getReferenceById(id);
  }

  @Override
  public VideoCommentDto toDto(VideoComment entity) {
    return new VideoCommentDto(
        entity.getId(), entity.getContents().getId(), entity.getCommenterUuid().getValue(),
        entity.getLikeCount(), entity.getCommentContent(), entity.getReplyCount(),
        entity.isModified(), entity.getCreatedAt()
    );
  }
}
