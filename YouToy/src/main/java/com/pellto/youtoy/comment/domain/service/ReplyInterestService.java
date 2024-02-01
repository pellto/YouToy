package com.pellto.youtoy.comment.domain.service;

import com.pellto.youtoy.comment.domain.port.in.ReplyInterestEventActions;
import com.pellto.youtoy.comment.domain.port.out.persistence.LoadReplyPort;
import com.pellto.youtoy.comment.domain.port.out.persistence.SaveReplyPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReplyInterestService implements ReplyInterestEventActions {

  private final LoadReplyPort loadReplyPort;
  private final SaveReplyPort saveReplyPort;

  @Override
  public void increaseLikeCount(Long replyId) {
    var reply = loadReplyPort.load(replyId);
    reply.increaseLikeCount();
    saveReplyPort.save(reply);
  }

  @Override
  public void decreaseLikeCount(Long replyId) {
    var reply = loadReplyPort.load(replyId);
    reply.decreaseLikeCount();
    saveReplyPort.save(reply);
  }
}
