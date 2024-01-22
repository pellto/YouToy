package com.pellto.youtoy.subscribe.domain.port.in;

import com.pellto.youtoy.global.dto.subscribe.SubscribeDto;
import com.pellto.youtoy.global.dto.subscribe.request.SubscribeRequest;

public interface SubscribeUsecase {

  SubscribeDto subscribe(SubscribeRequest request);
}
