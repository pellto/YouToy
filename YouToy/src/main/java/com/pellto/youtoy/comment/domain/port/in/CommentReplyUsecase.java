package com.pellto.youtoy.comment.domain.port.in;

public interface CommentReplyUsecase {

  void increaseCommentReplyCount(Long parentCommentId);

  void decreaseCommentReplyCount(Long parentCommentId);

  void removeAllRepliesByParentCommentId(Long commentId);
}
