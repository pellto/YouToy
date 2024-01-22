package com.pellto.youtoy.subscribe.domain.port.in;

import com.pellto.youtoy.global.dto.subscribe.request.ChangeSubscribeLevelRequest;

public interface ChangeSubscribeLevelUsecase {

  void changeLevel(ChangeSubscribeLevelRequest request);
}
