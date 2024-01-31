package com.pellto.youtoy.interest.application.adapter.out.event;

import com.pellto.youtoy.global.dto.interest.InterestDto;
import com.pellto.youtoy.global.event.interest.DeletedDislikeEvent;
import com.pellto.youtoy.global.event.interest.DeletedLikeEvent;
import com.pellto.youtoy.global.event.interest.DislikeEvent;
import com.pellto.youtoy.global.event.interest.LikeEvent;
import com.pellto.youtoy.global.interfaces.OutboundEventAdapter;
import com.pellto.youtoy.interest.domain.port.out.InterestEventPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

@OutboundEventAdapter
@RequiredArgsConstructor
@Slf4j
public class InterestEventOutAdapter implements InterestEventPort {


  private static final String PUBLISHER = "InterestEventOutAdapter";
  private final ApplicationEventPublisher applicationEventPublisher;

  @Override
  public void deletedDislikeEvent(InterestDto dto) {
    var publisher = PUBLISHER + "/deletedDislikeEvent";
    log.info(String.format("[%s]: 싫어요 삭제 완료 {dto: %s}", publisher, dto));

    var event = new DeletedDislikeEvent(dto, publisher);
    applicationEventPublisher.publishEvent(event);
  }

  @Override
  public void deletedLikeEvent(InterestDto dto) {
    var publisher = PUBLISHER + "/deletedLikeEvent";
    log.info(String.format("[%s]: 좋아요 삭제 완료 {dto: %s}", publisher, dto));

    var event = new DeletedLikeEvent(dto, publisher);
    applicationEventPublisher.publishEvent(event);
  }

  @Override
  public void likeEvent(InterestDto dto) {
    var publisher = PUBLISHER + "/likeEvent";
    log.info(String.format("[%s]: 좋아요 완료 {dto: %s}", publisher, dto));

    var event = new LikeEvent(dto, publisher);
    applicationEventPublisher.publishEvent(event);
  }

  @Override
  public void dislikeEvent(InterestDto dto) {
    var publisher = PUBLISHER + "/likeEvent";
    log.info(String.format("[%s]: 싫어요 완료 {dto: %s}", publisher, dto));

    var event = new DislikeEvent(dto, publisher);
    applicationEventPublisher.publishEvent(event);
  }
}
