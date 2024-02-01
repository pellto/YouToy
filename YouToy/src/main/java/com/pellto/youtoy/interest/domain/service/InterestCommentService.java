package com.pellto.youtoy.interest.domain.service;

import com.pellto.youtoy.interest.domain.model.InterestContentsType;
import com.pellto.youtoy.interest.domain.port.in.CommentEventActions;
import com.pellto.youtoy.interest.domain.port.out.SaveInterestPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InterestCommentService implements CommentEventActions {

  private static final String INTEREST_CONTENTS_TYPE = InterestContentsType.COMMENT.getType();
  private final SaveInterestPort saveInterestPort;

  @Override
  public void removeAllByCommentId(Long commentId) {
    saveInterestPort.deleteAllByContentsIdAndContentsType(commentId, INTEREST_CONTENTS_TYPE);
  }
}
