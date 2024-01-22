package com.pellto.youtoy.subscribe.domain.port.in;

import com.pellto.youtoy.global.dto.subscribe.request.UnsubscribeRequest;

public interface UnsubscribeUsecase {

  void unsubscribe(UnsubscribeRequest request);
}
