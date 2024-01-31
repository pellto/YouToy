package com.pellto.youtoy.post.domain.port.in;

import com.pellto.youtoy.global.event.comment.CommentWrittenEvent;

public interface CommentEventActions {

  void increaseCommentCount(CommentWrittenEvent event);

  // TODO: void decreaseCommentCount();
}
