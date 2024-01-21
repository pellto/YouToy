package com.pellto.youtoy.membership.application.adapter.out.event;

import com.pellto.youtoy.global.dto.member.MemberInfoDto;
import com.pellto.youtoy.global.dto.membership.MembershipDto;
import com.pellto.youtoy.global.event.membership.MembershipRemovedEvent;
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

  private static final String PUBLISHER = "MembershipEventAdapter";

  private final ApplicationEventPublisher applicationEventPublisher;


  @Override
  public void publishedInitMembership(Long membershipId, MemberInfoDto memberInfoDto) {
    var publisher = PUBLISHER + "/publishedInitMembership";
    log.info(String.format(
        "[%s]: 멤버십 발행 완료 {membershipId: %s, memberInfoDto: %s}",
        publisher, membershipId, memberInfoDto));

    var event = new PublishedInitMembershipEvent(membershipId, memberInfoDto, publisher);
    applicationEventPublisher.publishEvent(event);
  }

  @Override
  public void membershipRemovedEvent(MembershipDto dto) {
    var publisher = PUBLISHER + "/membershipRemovedEvent";
    log.info(String.format("[%s]: 멤버십 삭제 완료 {dto: %s}", publisher, dto));

    var event = new MembershipRemovedEvent(dto, publisher);
    applicationEventPublisher.publishEvent(event);
  }
}
