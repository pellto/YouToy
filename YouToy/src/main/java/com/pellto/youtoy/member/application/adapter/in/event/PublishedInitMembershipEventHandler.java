package com.pellto.youtoy.member.application.adapter.in.event;

import com.pellto.youtoy.global.event.membership.PublishedInitMembershipEvent;
import com.pellto.youtoy.member.domain.port.in.MemberSignUpUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PublishedInitMembershipEventHandler {

  private final MemberSignUpUsecase memberSignUpUsecase;

  @EventListener
  public void signUp(PublishedInitMembershipEvent event) throws InterruptedException {
    log.info(String.format("[PublishedInitMembershipHandler/signUp]: 회원 가입 시작(by %s) {event: %s}",
        event.getPublisher(), event));
    memberSignUpUsecase.signUp(event.getMembershipId(), event.getMemberInfo());
  }
}
