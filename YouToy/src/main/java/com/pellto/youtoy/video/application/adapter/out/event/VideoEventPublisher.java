package com.pellto.youtoy.video.application.adapter.out.event;

import com.pellto.youtoy.global.dto.video.VideoDto;
import com.pellto.youtoy.global.event.video.RequestedVideoUpload;
import com.pellto.youtoy.global.interfaces.OutboundEventAdapter;
import com.pellto.youtoy.video.domain.port.out.VideoEventPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

@OutboundEventAdapter
@RequiredArgsConstructor
@Slf4j
public class VideoEventPublisher implements VideoEventPort {

  private static final String PUBLISHER = "VideoEventPublisher";

  private final ApplicationEventPublisher applicationEventPublisher;

  @Override
  public void requestedVideoUpload(VideoDto dto) {
    var publisher = PUBLISHER + "/requestedVideoUpload";
    log.info(String.format(
        "[%s]: 업로드 요청 완료 {dto: %s}",
        publisher, dto
    ));
    var event = new RequestedVideoUpload(dto, publisher);
    applicationEventPublisher.publishEvent(event);
  }
}
