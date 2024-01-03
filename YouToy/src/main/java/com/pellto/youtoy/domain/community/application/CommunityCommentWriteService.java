package com.pellto.youtoy.domain.community.application;

import com.pellto.youtoy.domain.community.domain.CommunityComment;
import com.pellto.youtoy.domain.community.dto.CommunityCommentDto;
import com.pellto.youtoy.domain.community.dto.ModifyCommentRequest;
import com.pellto.youtoy.domain.community.dto.WriteCommentRequest;
import com.pellto.youtoy.domain.community.repository.CommunityCommentRepository;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import com.pellto.youtoy.global.exception.NotExistCommentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommunityCommentWriteService {

  private final CommunityCommentRepository commentRepository;
  private final CommunityCommentReadService commentReadService;
  private final CommentPostReadService commentPostReadService;

  public CommunityCommentDto write(WriteCommentRequest req) {
    var commenterUuid = new UserUUID(req.commenterUuid());
    var post = commentPostReadService.getById(req.commentPostId());
    var communityComment = CommunityComment.builder()
        .communityPost(post)
        .content(req.content())
        .commenterUuid(commenterUuid)
        .build();

    return commentReadService.toDto(commentRepository.save(communityComment));
  }

  public CommunityCommentDto modify(ModifyCommentRequest req) {
    var alreadyComment = commentRepository.findById(req.id())
        .orElseThrow(NotExistCommentException::new);
    alreadyComment.changeContent(req.content());
    return commentReadService.toDto(alreadyComment);
  }

  public void deleteById(Long id) {
    commentRepository.deleteById(id);
  }
}
