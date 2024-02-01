package com.pellto.youtoy.interest.domain.service;

import com.pellto.youtoy.interest.domain.model.InterestContentsType;
import com.pellto.youtoy.interest.domain.port.in.PostEventActions;
import com.pellto.youtoy.interest.domain.port.out.SaveInterestPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InterestPostService implements PostEventActions {

  private static final String INTEREST_CONTENTS_TYPE = InterestContentsType.POST.getType();
  private final SaveInterestPort saveInterestPort;

  @Override
  public void removeAllByPostId(Long postId) {
    saveInterestPort.deleteAllByContentsIdAndContentsType(postId, INTEREST_CONTENTS_TYPE);
  }
}
