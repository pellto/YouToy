package com.pellto.youtoy.channelManagement.application.adapter.out.event;


import com.pellto.youtoy.channelManagement.domain.port.out.ChannelManagementEventPort;
import com.pellto.youtoy.global.dto.channelManagement.ChannelManagementDto;
import com.pellto.youtoy.global.interfaces.OutboundEventAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@OutboundEventAdapter
@RequiredArgsConstructor
@Slf4j
public class ChannelManagementEventAdapter implements ChannelManagementEventPort {

  private static final String PUBLISHER = "ChannelManagementEventAdapter";
//  private final ApplicationEventPublisher applicationEventPublisher;

  @Override
  public void channelManagementChangedEvent(
      ChannelManagementDto before,
      ChannelManagementDto after
  ) {
    var publisher = PUBLISHER + "/channelManagementChangedEvent";
    log.info(
        String.format("[%s]: 채널 관리 레벨 변경 완료 {before: %s, after: %s}", publisher, before, after)
    );
  }
}
