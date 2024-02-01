package com.pellto.youtoy.comment.domain.port.in;

public interface CommentInterestEventActions {

  void increaseLikeCount(Long commentId);

  void decreaseLikeCount(Long commentId);
}
