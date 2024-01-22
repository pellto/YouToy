package com.pellto.youtoy.membership.application.adapter.in.event;

import com.pellto.youtoy.global.event.member.RequestedSignUpEvent;
import com.pellto.youtoy.global.interfaces.InboundEventAdapter;
import com.pellto.youtoy.membership.domain.port.in.PublishInitMembershipUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

@InboundEventAdapter
@RequiredArgsConstructor
@Slf4j
public class RequestedSignUpEventHandler {

  private final PublishInitMembershipUsecase publishInitMembershipUsecase;

  @EventListener
  public void renewInitMembership(RequestedSignUpEvent event) throws InterruptedException {
    log.info(String.format(
        "[RequestedSignUpEventHandler/renewInitMembership]: 최초 멤버십 갱신 완료 {event: %s}",
        event));

    publishInitMembershipUsecase.publish(event.getMemberInfoDto(), event.getRequestedAt());
  }
}
