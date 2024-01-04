package com.pellto.youtoy.domain.post.application;

import com.pellto.youtoy.domain.base.application.ReadService;
import com.pellto.youtoy.domain.post.domain.PostReply;
import com.pellto.youtoy.domain.post.dto.PostReplyDto;
import com.pellto.youtoy.domain.post.repository.PostReplyRepository;
import com.pellto.youtoy.global.exception.NotExistReplyException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostReplyReadService implements ReadService<PostReply, PostReplyDto> {

  private final PostReplyRepository replyRepository;
  private final PostCommentReadService parentCommentReadService;


  public List<PostReplyDto> findAllByParentId(Long parentId) {
    var parentComment = parentCommentReadService.getById(parentId);
    return replyRepository.findAllByParentComment(parentComment).stream().map(this::toDto).toList();
  }

  @Override
  public List<PostReplyDto> findAll() {
    return replyRepository.findAll().stream().map(this::toDto).toList();
  }

  @Override
  public PostReplyDto findById(Long id) {
    var reply = replyRepository.findById(id)
        .orElseThrow(NotExistReplyException::new);
    return toDto(reply);
  }

  @Override
  public PostReply getById(Long id) {
    return replyRepository.getReferenceById(id);
  }

  @Override
  public PostReplyDto toDto(PostReply reply) {
    return new PostReplyDto(
        reply.getId(),
        reply.getParentComment().getId(),
        reply.getCommenterUuid().getValue(),
        reply.getLikeCount(),
        reply.getCommentContent(),
        reply.isModified(),
        reply.getCreatedAt()
    );
  }
}
