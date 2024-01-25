package com.pellto.youtoy.global.event.channel;

import com.pellto.youtoy.global.dto.channel.ChannelDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ChannelCreatedEvent {

  private ChannelDto dto;
  private String publisher;
}
