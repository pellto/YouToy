package com.pellto.youtoy.comment.domain.service;

import com.pellto.youtoy.comment.domain.port.in.CommentInterestEventActions;
import com.pellto.youtoy.comment.domain.port.out.persistence.LoadCommentPort;
import com.pellto.youtoy.comment.domain.port.out.persistence.SaveCommentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentInterestService implements CommentInterestEventActions {

  private final LoadCommentPort loadCommentPort;
  private final SaveCommentPort saveCommentPort;

  @Override
  public void increaseLikeCount(Long commentId) {
    var comment = loadCommentPort.load(commentId);
    comment.increaseLikeCount();
    saveCommentPort.save(comment);
  }

  @Override
  public void decreaseLikeCount(Long commentId) {
    var comment = loadCommentPort.load(commentId);
    comment.decreaseLikeCount();
    saveCommentPort.save(comment);
  }
}
