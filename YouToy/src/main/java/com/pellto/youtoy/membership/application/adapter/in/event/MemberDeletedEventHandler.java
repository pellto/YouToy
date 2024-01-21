package com.pellto.youtoy.membership.application.adapter.in.event;

import com.pellto.youtoy.global.event.member.MemberDeletedEvent;
import com.pellto.youtoy.global.interfaces.InboundEventAdapter;
import com.pellto.youtoy.membership.domain.port.in.RemoveMembershipUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

@InboundEventAdapter("MemberDeletedEventHandlerInMembership")
@RequiredArgsConstructor
@Slf4j
public class MemberDeletedEventHandler {

  private final RemoveMembershipUsecase removeMembershipUsecase;

  @EventListener
  public void removeMembership(MemberDeletedEvent event) throws InterruptedException {
    log.info(
        String.format("[MemberDeletedEventHandler/removeMembership]: 멤버십 삭제 시작(by %s) {dto: %s}",
            event.getPublisher(), event.getDto()));

    removeMembershipUsecase.remove(event.getDto());
  }
}
