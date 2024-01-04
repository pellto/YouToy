package com.pellto.youtoy.domain.video.application;

import com.pellto.youtoy.domain.base.application.WriteUpdateDeleteService;
import com.pellto.youtoy.domain.base.dto.ModifyCommentRequest;
import com.pellto.youtoy.domain.base.dto.WriteCommentRequest;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import com.pellto.youtoy.domain.video.domain.VideoReplyComment;
import com.pellto.youtoy.domain.video.dto.VideoReplyCommentDto;
import com.pellto.youtoy.domain.video.repository.VideoReplyCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class VideoReplyWriteService implements
    WriteUpdateDeleteService<VideoReplyCommentDto, WriteCommentRequest, ModifyCommentRequest> {

  private final VideoReplyCommentRepository videoReplyCommentRepository;
  private final VideoReplyReadService videoReplyReadService;
  private final VideoCommentReadService contentReadService;

  @Override
  public VideoReplyCommentDto write(WriteCommentRequest writeRequest) {
    var parentComment = contentReadService.getById(writeRequest.contentId());
    var reply = VideoReplyComment.builder()
        .parentComment(parentComment)
        .content(writeRequest.content())
        .commenterUuid(new UserUUID(writeRequest.commenterUuid()))
        .build();
    return videoReplyReadService.toDto(videoReplyCommentRepository.save(reply));
  }

  @Override
  public VideoReplyCommentDto modify(ModifyCommentRequest modifyRequest) {
    var reply = videoReplyCommentRepository.getReferenceById(modifyRequest.id());
    reply.changeCommentContent(modifyRequest.content());
    return videoReplyReadService.toDto(videoReplyCommentRepository.save(reply));
  }

  @Override
  public void deleteById(Long id) {
    videoReplyCommentRepository.deleteById(id);
  }
}
