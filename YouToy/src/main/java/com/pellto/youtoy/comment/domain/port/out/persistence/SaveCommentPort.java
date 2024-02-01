package com.pellto.youtoy.comment.domain.port.out.persistence;

import com.pellto.youtoy.comment.domain.model.Comment;
import com.pellto.youtoy.comment.domain.model.CommentContentsType;

public interface SaveCommentPort {

  void deleteById(Long commentId);

  Comment save(Comment comment);

  void update(Comment comment);

  void deleteAllByContentsIdAndContentsType(Long contentsId,
      CommentContentsType commentContentsType);
}
