package com.pellto.youtoy.comment.application.adapter.out.persistence.reply;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReplyJpaDataRepository extends JpaRepository<ReplyEntity, Long> {

  List<ReplyEntity> findAllByParentCommentId(Long parentCommentId);

  @Query("select r.id "
      + "from ReplyEntity r "
      + "where r.parentCommentId = :parentCommentId")
  List<Long> findAllIdsByParentCommentId(@Param("parentCommentId") Long parentCommentId);
}
