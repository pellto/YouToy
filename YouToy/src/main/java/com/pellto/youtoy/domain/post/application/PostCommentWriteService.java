package com.pellto.youtoy.domain.post.application;

import com.pellto.youtoy.domain.base.application.WriteUpdateDeleteService;
import com.pellto.youtoy.domain.base.dto.ModifyCommentRequest;
import com.pellto.youtoy.domain.base.dto.WriteCommentRequest;
import com.pellto.youtoy.domain.post.domain.PostComment;
import com.pellto.youtoy.domain.post.dto.PostCommentDto;
import com.pellto.youtoy.domain.post.repository.PostCommentRepository;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import com.pellto.youtoy.global.exception.NotExistPostException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostCommentWriteService implements
    WriteUpdateDeleteService<PostCommentDto, WriteCommentRequest, ModifyCommentRequest> {

  private final PostCommentRepository commentRepository;
  private final PostCommentReadService commentReadService;
  private final PostReadService postReadService;

  @Override
  public PostCommentDto write(WriteCommentRequest writeRequest) {
    var commenterUuid = new UserUUID(writeRequest.commenterUuid());
    var post = postReadService.getById(writeRequest.contentId());
    var communityComment = PostComment.builder()
        .contents(post)
        .commentContent(writeRequest.content())
        .commenterUuid(commenterUuid)
        .build();

    return commentReadService.toDto(commentRepository.save(communityComment));
  }

  @Override
  public PostCommentDto modify(ModifyCommentRequest modifyRequest) {
    var comment = commentRepository.findById(modifyRequest.id())
        .orElseThrow(NotExistPostException::new);

    comment.changeCommentContent(modifyRequest.content());

    return commentReadService.toDto(comment);
  }

  public void deleteById(Long id) {
    commentRepository.deleteById(id);
  }
}
