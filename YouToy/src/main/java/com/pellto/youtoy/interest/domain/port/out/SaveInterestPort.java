package com.pellto.youtoy.interest.domain.port.out;

import com.pellto.youtoy.interest.domain.model.Interest;

public interface SaveInterestPort {

  void delete(Interest interest);

  void deleteAllByContentsIdAndContentsType(Long contentsId, String interestContentsType);

  Interest save(Interest interest);
}
