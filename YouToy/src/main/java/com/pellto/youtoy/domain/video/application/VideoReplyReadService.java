package com.pellto.youtoy.domain.video.application;

import com.pellto.youtoy.domain.base.application.ReadService;
import com.pellto.youtoy.domain.video.domain.VideoReply;
import com.pellto.youtoy.domain.video.dto.VideoReplyDto;
import com.pellto.youtoy.domain.video.repository.VideoReplyRepository;
import com.pellto.youtoy.global.exception.NotExistReplyException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class VideoReplyReadService implements
    ReadService<VideoReply, VideoReplyDto> {

  private final VideoReplyRepository videoReplyRepository;
  private final VideoCommentReadService contentReadService;

  @Override
  public List<VideoReplyDto> findAll() {
    return videoReplyRepository.findAll().stream().map(this::toDto).toList();
  }

  public List<VideoReplyDto> findAllByParentId(Long parentId) {
    var parentComment = contentReadService.getById(parentId);
    return videoReplyRepository.findAllByParentComment(parentComment).stream()
        .map(this::toDto).toList();
  }

  @Override
  public VideoReplyDto findById(Long id) {
    var reply = videoReplyRepository.findById(id)
        .orElseThrow(NotExistReplyException::new);
    return toDto(reply);
  }

  @Override
  public VideoReply getById(Long id) {
    return videoReplyRepository.getReferenceById(id);
  }

  @Override
  public VideoReplyDto toDto(VideoReply entity) {
    return new VideoReplyDto(entity.getId(), entity.getParentComment().getId(),
        entity.getCommenterUuid().getValue(),
        entity.getLikeCount(), entity.getCommentContent(), entity.isModified(),
        entity.getCreatedAt());
  }
}
