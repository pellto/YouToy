package com.pellto.youtoy.global.event.subscribe;

import com.pellto.youtoy.global.dto.subscribe.SubscribeDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class SubscribedChannelEvent {

  private SubscribeDto subscribeDto;
  private String publisher;
}
