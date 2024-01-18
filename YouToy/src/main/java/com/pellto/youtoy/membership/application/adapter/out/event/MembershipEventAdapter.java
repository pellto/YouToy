package com.pellto.youtoy.membership.application.adapter.out.event;

import com.pellto.youtoy.global.dto.member.MemberInfoDto;
import com.pellto.youtoy.global.event.membership.PublishedInitMembershipEvent;
import com.pellto.youtoy.membership.domain.port.out.MembershipEventPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MembershipEventAdapter implements MembershipEventPort {

  private final ApplicationEventPublisher applicationEventPublisher;


  @Override
  public void publishedInitMembership(Long membershipId, MemberInfoDto memberInfoDto) {

    log.info(String.format(
        "[MembershipEventAdapter/publishedInitMembership]: 멤버십 발행 완료 {membershipId: %s, memberInfoDto: %s}",
        membershipId, memberInfoDto));
    applicationEventPublisher.publishEvent(
        new PublishedInitMembershipEvent(membershipId, memberInfoDto));
  }
}
