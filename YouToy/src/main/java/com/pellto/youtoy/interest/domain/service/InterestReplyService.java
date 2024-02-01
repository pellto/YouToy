package com.pellto.youtoy.interest.domain.service;

import com.pellto.youtoy.interest.domain.model.InterestContentsType;
import com.pellto.youtoy.interest.domain.port.in.ReplyEventActions;
import com.pellto.youtoy.interest.domain.port.out.SaveInterestPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InterestReplyService implements ReplyEventActions {

  private static final String INTEREST_CONTENTS_TYPE = InterestContentsType.REPLY.getType();
  private final SaveInterestPort saveInterestPort;

  @Override
  public void removeAllByReplyId(Long replyId) {
    saveInterestPort.deleteAllByContentsIdAndContentsType(replyId, INTEREST_CONTENTS_TYPE);
  }
}
