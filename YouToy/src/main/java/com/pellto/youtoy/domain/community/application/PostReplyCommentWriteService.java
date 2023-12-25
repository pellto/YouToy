package com.pellto.youtoy.domain.community.application;

import com.pellto.youtoy.domain.community.domain.PostReplyComment;
import com.pellto.youtoy.domain.community.dto.PostReplyCommentDto;
import com.pellto.youtoy.domain.community.dto.WriteReplyRequest;
import com.pellto.youtoy.domain.community.repository.PostReplyRepository;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostReplyCommentWriteService {

  private final PostReplyRepository replyRepository;
  private final PostReplyCommentReadService replyCommentReadService;
  private final CommunityCommentReadService commentReadService;

  public PostReplyCommentDto write(WriteReplyRequest req) {
    var parentComment = commentReadService.getById(req.parentCommentId());
    var commenter = new UserUUID(req.commenterUuid());
    var reply = PostReplyComment.builder()
        .parentComment(parentComment)
        .content(req.content())
        .commenterUuid(commenter)
        .build();
    return replyCommentReadService.toDto(replyRepository.save(reply));
  }
}
