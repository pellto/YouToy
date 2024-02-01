package com.pellto.youtoy.comment.application.adapter.out.persistence;

import com.pellto.youtoy.comment.domain.model.Comment;
import com.pellto.youtoy.comment.domain.model.CommentContentsType;
import com.pellto.youtoy.comment.domain.port.out.persistence.LoadCommentPort;
import com.pellto.youtoy.comment.domain.port.out.persistence.SaveCommentPort;
import com.pellto.youtoy.global.interfaces.PersistenceAdapter;
import java.util.List;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class CommentPersistenceAdapter implements LoadCommentPort, SaveCommentPort {

  private final CommentJpaDataRepository jpaDataRepository;
  private final CommentMapper commentMapper;

  @Override
  public boolean isExistById(Long commentId) {
    return jpaDataRepository.existsById(commentId);
  }

  @Override
  public Comment load(Long commentId) {
    var entity = jpaDataRepository.findById(commentId).orElseThrow(
        () -> new IllegalArgumentException("댓글 없음")
    );
    return commentMapper.toDomain(entity);
  }

  @Override
  public List<Comment> loadAllByContentsTypeAndContentsId(
      String commentContentsType, Long contentsId) {
    var commentEntities = jpaDataRepository.findAllByCommentContentsTypeAndContentsId(
        commentContentsType, contentsId);
    return commentEntities.stream().map(commentMapper::toDomain).toList();
  }

  @Override
  public List<Long> loadAllIdsByContentsTypeAndContentsId(String commentContentsType,
      Long contentsId) {
    return jpaDataRepository.findAllIdsByContentsTypeAndContentsId(commentContentsType, contentsId);
  }

  @Override
  public void deleteById(Long commentId) {
    jpaDataRepository.deleteById(commentId);
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

  @Override
  public void deleteAllByContentsIdAndContentsType(
      Long contentsId, CommentContentsType commentContentsType) {
    // TODO: change deleteAll*InBatch
    jpaDataRepository.deleteAllByContentsIdAndCommentContentsType(contentsId,
        commentContentsType.getType());
  }
}
