package com.pellto.youtoy.interest.domain.port.in;

public interface CommentEventActions {

  void removeAllByCommentId(Long commentId);
}
