package com.pellto.youtoy.domain.community.application;

import com.pellto.youtoy.domain.community.domain.PostReplyComment;
import com.pellto.youtoy.domain.community.dto.PostReplyCommentDto;
import com.pellto.youtoy.domain.community.repository.PostReplyRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostReplyCommentReadService {

  private final PostReplyRepository replyRepository;
  private final CommunityCommentReadService parentCommentReadService;

  public List<PostReplyCommentDto> findAllByParentId(Long parentId) {
    var parentComment = parentCommentReadService.getById(parentId);
    return replyRepository.findAllByParentComment(parentComment).stream().map(this::toDto).toList();
  }

  public List<PostReplyCommentDto> findAll() {
    return replyRepository.findAll().stream().map(this::toDto).toList();
  }

  public PostReplyCommentDto toDto(PostReplyComment replyComment) {
    return new PostReplyCommentDto(
        replyComment.getId(),
        replyComment.getParentComment().getId(),
        replyComment.getCommenterUuid().getValue(),
        replyComment.getLikeCount(),
        replyComment.getContent(),
        replyComment.isModified(),
        replyComment.getCreatedAt()
    );
  }
}
