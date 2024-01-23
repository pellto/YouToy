package com.pellto.youtoy.channel.domain.port.in;

public interface IncrementSubscriberUsecase {

  void increase(Long channelId);

  void decrease(Long channelId);

}
