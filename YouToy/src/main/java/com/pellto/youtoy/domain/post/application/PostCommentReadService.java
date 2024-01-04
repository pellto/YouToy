package com.pellto.youtoy.domain.post.application;

import com.pellto.youtoy.domain.base.application.ReadService;
import com.pellto.youtoy.domain.post.domain.PostComment;
import com.pellto.youtoy.domain.post.dto.PostCommentDto;
import com.pellto.youtoy.domain.post.repository.PostCommentRepository;
import com.pellto.youtoy.global.exception.NotExistCommentException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostCommentReadService implements ReadService<PostComment, PostCommentDto> {

  private final PostCommentRepository postCommentRepository;

  @Override
  public List<PostCommentDto> findAll() {
    return postCommentRepository.findAll().stream().map(this::toDto).toList();
  }

  @Override
  public PostCommentDto findById(Long id) {
    var comment = postCommentRepository.findById(id).orElseThrow(
        NotExistCommentException::new);
    return toDto(comment);
  }

  @Override
  public PostComment getById(Long id) {
    return postCommentRepository.getReferenceById(id);
  }

  @Override
  public PostCommentDto toDto(PostComment comment) {
    return new PostCommentDto(
        comment.getId(),
        comment.getContents().getId(),
        comment.getCommenterUuid().getValue(),
        comment.getLikeCount(),
        comment.getCommentContent(),
        comment.getReplies().size(),
        comment.isModified(),
        comment.getCreatedAt()
    );
  }
}
