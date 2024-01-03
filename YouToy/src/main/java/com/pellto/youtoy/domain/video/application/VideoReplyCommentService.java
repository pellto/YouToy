package com.pellto.youtoy.domain.video.application;

import com.pellto.youtoy.domain.base.application.ReadService;
import com.pellto.youtoy.domain.base.application.WriteUpdateDeleteService;
import com.pellto.youtoy.domain.base.dto.ModifyCommentRequest;
import com.pellto.youtoy.domain.base.dto.WriteCommentRequest;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import com.pellto.youtoy.domain.video.domain.VideoReplyComment;
import com.pellto.youtoy.domain.video.dto.VideoReplyCommentDto;
import com.pellto.youtoy.domain.video.repository.VideoReplyCommentRepository;
import com.pellto.youtoy.global.exception.NotExistReplyCommentException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class VideoReplyCommentService implements
    WriteUpdateDeleteService<VideoReplyCommentDto, WriteCommentRequest, ModifyCommentRequest>,
    ReadService<VideoReplyComment, VideoReplyCommentDto> {

  private final VideoReplyCommentRepository videoReplyCommentRepository;
  private final VideoCommentReadService contentReadService;

  @Override
  public List<VideoReplyCommentDto> findAll() {
    return videoReplyCommentRepository.findAll().stream().map(this::toDto).toList();
  }

  public List<VideoReplyCommentDto> findAllByParentId(Long parentId) {
    var parentComment = contentReadService.getById(parentId);
    return videoReplyCommentRepository.findAllByParentComment(parentComment).stream()
        .map(this::toDto).toList();
  }

  @Override
  public VideoReplyCommentDto findById(Long id) {
    var nullableReply = videoReplyCommentRepository.findById(id)
        .orElseThrow(NotExistReplyCommentException::new);
    return toDto(nullableReply);
  }

  @Override
  public VideoReplyComment getById(Long id) {
    return videoReplyCommentRepository.getReferenceById(id);
  }

  @Override
  public VideoReplyCommentDto toDto(VideoReplyComment entity) {
    return new VideoReplyCommentDto(entity.getId(), entity.getParentComment().getId(),
        entity.getCommenterUuid().getValue(),
        entity.getLikeCount(), entity.getCommentContent(), entity.isModified(),
        entity.getCreatedAt());
  }

  @Override
  public VideoReplyCommentDto write(WriteCommentRequest writeRequest) {
    var parentComment = contentReadService.getById(writeRequest.contentId());
    var reply = VideoReplyComment.builder()
        .parentComment(parentComment)
        .content(writeRequest.content())
        .commenterUuid(new UserUUID(writeRequest.commenterUuid()))
        .build();
    return toDto(videoReplyCommentRepository.save(reply));
  }

  @Override
  public VideoReplyCommentDto modify(ModifyCommentRequest modifyRequest) {
    var reply = videoReplyCommentRepository.getReferenceById(modifyRequest.id());
    reply.changeCommentContent(modifyRequest.content());
    return toDto(videoReplyCommentRepository.save(reply));
  }

  @Override
  public void deleteById(Long id) {
    videoReplyCommentRepository.deleteById(id);
  }
}
