package com.pellto.youtoy.subscribe.domain.port.out;

import com.pellto.youtoy.subscribe.domain.model.Subscribe;
import java.util.List;

public interface LoadSubscribePort {

  Subscribe load(Long id);

  Subscribe loadBySubscriberIdAndSubscribedChannelId(Long subscriberId, Long subscribedChannelId);

  List<Subscribe> loadsBySubscriberId(Long subscriberId);
}
