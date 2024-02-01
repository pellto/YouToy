package com.pellto.youtoy.comment.domain.port.in;

public interface InterestEventActions {

  void increaseLikeCount(Long commentId);

  void decreaseLikeCount(Long commentId);
}
