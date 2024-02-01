package com.pellto.youtoy.comment.domain.service;

import com.pellto.youtoy.comment.domain.port.in.CommentReplyUsecase;
import com.pellto.youtoy.comment.domain.port.out.persistence.LoadCommentPort;
import com.pellto.youtoy.comment.domain.port.out.persistence.LoadReplyPort;
import com.pellto.youtoy.comment.domain.port.out.persistence.SaveCommentPort;
import com.pellto.youtoy.comment.domain.port.out.persistence.SaveReplyPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentReplyService implements CommentReplyUsecase {

  private final LoadCommentPort loadCommentPort;
  private final SaveCommentPort saveCommentPort;
  private final LoadReplyPort loadReplyPort;
  private final SaveReplyPort saveReplyPort;

  @Override
  public void increaseCommentReplyCount(Long parentCommentId) {
    var comment = loadCommentPort.load(parentCommentId);
    comment.increaseReplyCount();
    saveCommentPort.save(comment);
  }

  @Override
  public void decreaseCommentReplyCount(Long parentCommentId) {
    var comment = loadCommentPort.load(parentCommentId);
    comment.decreaseReplyCount();
    saveCommentPort.save(comment);
  }

  @Override
  public void removeAllRepliesByParentCommentId(Long commentId) {
    var replyIds = loadReplyPort.loadAllIdsByParentCommentId(commentId);
    replyIds.forEach(saveReplyPort::deleteById);
  }
}
