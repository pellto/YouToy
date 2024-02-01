package com.pellto.youtoy.comment.domain.port.in;

public interface ReplyInterestEventActions {

  void increaseLikeCount(Long replyId);

  void decreaseLikeCount(Long replyId);
}
