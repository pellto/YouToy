package com.pellto.youtoy.domain.community.application;

import com.pellto.youtoy.domain.community.domain.CommunityComment;
import com.pellto.youtoy.domain.community.dto.CommunityCommentDto;
import com.pellto.youtoy.domain.community.dto.ModifyCommentRequest;
import com.pellto.youtoy.domain.community.dto.WriteCommentRequest;
import com.pellto.youtoy.domain.community.repository.CommunityCommentRepository;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommunityCommentWriteService {

  private final CommunityCommentRepository commentRepository;
  private final CommunityCommentReadService commentReadService;

  public CommunityCommentDto write(WriteCommentRequest req) {
    var commenterUuid = new UserUUID(req.commenterUuid());
    var communityComment = CommunityComment.builder()
        .communityPostId(req.commentPostId())
        .content(req.content())
        .commenterUuid(commenterUuid)
        .build();

    return commentReadService.toDto(commentRepository.save(communityComment));
  }

  public CommunityCommentDto modify(ModifyCommentRequest req) {
    var alreadyComment = commentRepository.getReferenceById(req.id());
    alreadyComment.changeContent(req.content());
    return commentReadService.toDto(alreadyComment);
  }
}
