package com.pellto.youtoy.channel.application.adapter.out.event;

import com.pellto.youtoy.channel.domain.port.out.ChannelEventPort;
import com.pellto.youtoy.global.dto.channel.ChannelDto;
import com.pellto.youtoy.global.event.channel.ChannelCreatedEvent;
import com.pellto.youtoy.global.event.channel.ChannelDeletedEvent;
import com.pellto.youtoy.global.interfaces.OutboundEventAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

@OutboundEventAdapter
@RequiredArgsConstructor
@Slf4j
public class ChannelEventAdapter implements ChannelEventPort {

  private static final String PUBLISHER = "ChannelEventAdapter";
  private final ApplicationEventPublisher applicationEventPublisher;

  @Override
  public void channelDeletedEvent(ChannelDto dto) {
    var publisher = PUBLISHER + "/channelDeletedEvent";
    log.info(String.format("[%s]: 채널 삭제 완료 {ChannelDto: %s}", publisher, dto));

    var event = new ChannelDeletedEvent(dto, publisher);
    applicationEventPublisher.publishEvent(event);
  }

  @Override
  public void channelCreatedEvent(ChannelDto dto) {
    var publisher = PUBLISHER + "/channelCreatedEvent";
    log.info(String.format("[%s]: 채널 생성 완료 {ChannelDto: %s}", publisher, dto));

    var event = new ChannelCreatedEvent(dto, publisher);
    applicationEventPublisher.publishEvent(event);
  }
}
