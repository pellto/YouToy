package com.pellto.youtoy.subscribe.domain.port.out;

import com.pellto.youtoy.subscribe.domain.model.Subscribe;

public interface SaveSubscribePort {

  Subscribe save(Subscribe subscribe);

  void delete(Subscribe subscribe);
}
