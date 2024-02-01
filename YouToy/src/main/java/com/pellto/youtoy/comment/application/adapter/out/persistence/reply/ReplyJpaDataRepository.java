package com.pellto.youtoy.comment.application.adapter.out.persistence.reply;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyJpaDataRepository extends JpaRepository<ReplyEntity, Long> {

  List<ReplyEntity> findAllByParentCommentId(Long parentCommentId);
}
