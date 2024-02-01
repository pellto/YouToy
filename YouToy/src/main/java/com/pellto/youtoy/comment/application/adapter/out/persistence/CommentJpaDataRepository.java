package com.pellto.youtoy.comment.application.adapter.out.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaDataRepository extends JpaRepository<CommentEntity, Long> {

  void deleteAllByContentsIdAndCommentContentsType(Long contentsId, String contentsType);

  List<CommentEntity> findAllByCommentContentsTypeAndContentsId(
      String commentContentsType, Long contentsId);
}
