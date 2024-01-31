package com.pellto.youtoy.comment.application.adapter.out.persistence;

import com.pellto.youtoy.comment.domain.model.Comment;
import com.pellto.youtoy.comment.domain.port.out.persistence.LoadCommentPort;
import com.pellto.youtoy.comment.domain.port.out.persistence.SaveCommentPort;
import com.pellto.youtoy.global.interfaces.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class CommentPersistenceAdapter implements LoadCommentPort, SaveCommentPort {

  private final CommentJpaDataRepository jpaDataRepository;
  private final CommentMapper commentMapper;


  @Override
  public Comment load(Long commentId) {
    var entity = jpaDataRepository.findById(commentId).orElseThrow(
        () -> new IllegalArgumentException("댓글 없음")
    );
    return commentMapper.toDomain(entity);
  }

  @Override
  public Comment save(Comment comment) {
    var entity = commentMapper.toEntity(comment);
    entity = jpaDataRepository.save(entity);
    return commentMapper.toDomain(entity);
  }

  @Override
  public void update(Comment comment) {
    var entity = commentMapper.toEntity(comment);
    jpaDataRepository.save(entity);
  }
}
