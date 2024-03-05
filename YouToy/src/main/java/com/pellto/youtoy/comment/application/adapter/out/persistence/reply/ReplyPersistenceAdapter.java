package com.pellto.youtoy.comment.application.adapter.out.persistence.reply;

import com.pellto.youtoy.comment.domain.model.Reply;
import com.pellto.youtoy.comment.domain.port.out.persistence.LoadReplyPort;
import com.pellto.youtoy.comment.domain.port.out.persistence.SaveReplyPort;
import com.pellto.youtoy.global.interfaces.PersistenceAdapter;
import java.util.List;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class ReplyPersistenceAdapter implements LoadReplyPort, SaveReplyPort {

  private final ReplyMapper replyMapper;
  private final ReplyJpaDataRepository jpaDataRepository;

  @Override
  public void deleteById(Long replyId) {
    jpaDataRepository.deleteById(replyId);
  }

  @Override
  public Reply save(Reply reply) {
    var entity = replyMapper.toEntity(reply);
    entity = jpaDataRepository.save(entity);
    return replyMapper.toDomain(entity);
  }

  @Override
  public void update(Reply reply) {
    var entity = replyMapper.toEntity(reply);
    jpaDataRepository.save(entity);
  }

  @Override
  public Reply load(Long replyId) {
    var entity = jpaDataRepository.findById(replyId)
        .orElseThrow(() -> new IllegalArgumentException("reply 없음"));
    return replyMapper.toDomain(entity);
  }

  @Override
  public List<Reply> loadAllByParentCommentId(Long parentCommentId) {
    var entities = jpaDataRepository.findAllByParentCommentId(parentCommentId);
    return entities.stream().map(replyMapper::toDomain).toList();
  }

  @Override
  public List<Long> loadAllIdsByParentCommentId(Long parentCommentId) {
    return jpaDataRepository.findAllIdsByParentCommentId(parentCommentId);
  }

  @Override
  public void deleteAllByParentCommentId(Long parentCommentId) {
    jpaDataRepository.deleteByParentCommentId(parentCommentId);
  }

  @Override
  public void deleteAllByParentCommentIdIn(List<Long> parentCommentIds) {
    jpaDataRepository.deleteByParentCommentsIdIn(parentCommentIds);
  }
}
