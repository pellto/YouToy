package com.pellto.youtoy.interest.domain.port.out;

import com.pellto.youtoy.interest.domain.model.Interest;

public interface SaveInterestPort {

  void delete(Interest interest);

  Interest save(Interest interest);
}
