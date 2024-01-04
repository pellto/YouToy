package com.pellto.youtoy.domain.video.application;

import com.pellto.youtoy.domain.base.application.WriteUpdateDeleteService;
import com.pellto.youtoy.domain.base.dto.ModifyCommentRequest;
import com.pellto.youtoy.domain.base.dto.WriteCommentRequest;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import com.pellto.youtoy.domain.video.domain.VideoReply;
import com.pellto.youtoy.domain.video.dto.VideoReplyDto;
import com.pellto.youtoy.domain.video.repository.VideoReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class VideoReplyWriteService implements
    WriteUpdateDeleteService<VideoReplyDto, WriteCommentRequest, ModifyCommentRequest> {

  private final VideoReplyRepository videoReplyRepository;
  private final VideoReplyReadService videoReplyReadService;
  private final VideoCommentReadService contentReadService;

  @Override
  public VideoReplyDto write(WriteCommentRequest writeRequest) {
    var parentComment = contentReadService.getById(writeRequest.contentId());
    var reply = VideoReply.builder()
        .parentComment(parentComment)
        .content(writeRequest.content())
        .commenterUuid(new UserUUID(writeRequest.commenterUuid()))
        .build();
    return videoReplyReadService.toDto(videoReplyRepository.save(reply));
  }

  @Override
  public VideoReplyDto modify(ModifyCommentRequest modifyRequest) {
    var reply = videoReplyRepository.getReferenceById(modifyRequest.id());
    reply.changeCommentContent(modifyRequest.content());
    return videoReplyReadService.toDto(videoReplyRepository.save(reply));
  }

  @Override
  public void deleteById(Long id) {
    videoReplyRepository.deleteById(id);
  }
}
