package com.pellto.youtoy.comment.domain.port.out.persistence;

import com.pellto.youtoy.comment.domain.model.Comment;

public interface SaveCommentPort {

  Comment save(Comment comment);

  void update(Comment comment);
}
