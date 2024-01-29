package com.pellto.youtoy.interest.domain.port.out;

import com.pellto.youtoy.interest.domain.model.Interest;

public interface LoadInterestPort {

  Interest load(Long id);
}
