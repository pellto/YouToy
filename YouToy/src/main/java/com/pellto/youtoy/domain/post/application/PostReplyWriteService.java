package com.pellto.youtoy.domain.post.application;

import com.pellto.youtoy.domain.base.application.WriteUpdateDeleteService;
import com.pellto.youtoy.domain.base.dto.ModifyCommentRequest;
import com.pellto.youtoy.domain.base.dto.WriteCommentRequest;
import com.pellto.youtoy.domain.post.domain.PostReply;
import com.pellto.youtoy.domain.post.dto.PostReplyDto;
import com.pellto.youtoy.domain.post.repository.PostReplyRepository;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostReplyWriteService implements
    WriteUpdateDeleteService<PostReplyDto, WriteCommentRequest, ModifyCommentRequest> {

  private final PostReplyRepository replyRepository;
  private final PostReplyReadService replyReadService;
  private final PostCommentReadService commentReadService;

  @Override
  public PostReplyDto write(WriteCommentRequest writeRequest) {
    var parentComment = commentReadService.getById(writeRequest.contentId());
    var commenter = new UserUUID(writeRequest.commenterUuid());
    var reply = PostReply.builder()
        .parentComment(parentComment)
        .content(writeRequest.content())
        .commenterUuid(commenter)
        .build();
    return replyReadService.toDto(replyRepository.save(reply));
  }

  @Override
  public PostReplyDto modify(ModifyCommentRequest modifyRequest) {
    var reply = replyRepository.getReferenceById(modifyRequest.id());
    reply.changeCommentContent(modifyRequest.content());
    return replyReadService.toDto(reply);
  }

  @Override
  public void deleteById(Long id) {
    replyRepository.deleteById(id);
  }
}
