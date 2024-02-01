package com.pellto.youtoy.comment.application.adapter.out.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentJpaDataRepository extends JpaRepository<CommentEntity, Long> {

  void deleteAllByContentsIdAndCommentContentsType(Long contentsId, String contentsType);

  List<CommentEntity> findAllByCommentContentsTypeAndContentsId(
      String commentContentsType, Long contentsId);

  @Query("select c.id "
      + "from CommentEntity c "
      + "where c.commentContentsType = :commentContentsType and c.contentsId = :contentsId")
  List<Long> findAllIdsByContentsTypeAndContentsId(
      @Param("commentContentsType") String commentContentsType,
      @Param("contentsId") Long contentsId
  );

}
