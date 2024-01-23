package com.pellto.youtoy.subscribe.domain.port.in;

import java.util.List;

public interface GetSubscribedChannelUsecase {

  List<Long> getChannels(Long subscriberId);
}
