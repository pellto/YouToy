package com.pellto.youtoy.member.application.adapter.out.event;

import com.pellto.youtoy.global.dto.member.MemberDto;
import com.pellto.youtoy.global.dto.member.MemberInfoDto;
import com.pellto.youtoy.global.event.member.MemberDeletedEvent;
import com.pellto.youtoy.global.event.member.RequestedSignUpEvent;
import com.pellto.youtoy.global.event.member.SignedUpEvent;
import com.pellto.youtoy.global.interfaces.OutboundEventAdapter;
import com.pellto.youtoy.member.domain.port.out.MemberEventPort;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

@OutboundEventAdapter
@RequiredArgsConstructor
@Slf4j
public class MemberEventAdapter implements MemberEventPort {

  private final ApplicationEventPublisher applicationEventPublisher;

  @Override
  public void memberDeletedEvent(MemberDto dto) {
    var publisher = "MemberEventAdapter/memberDeletedEvent";
    log.info(String.format("[%s]: 회원 삭제 완료 {MemberDto: %s}",
        publisher, dto));

    var event = new MemberDeletedEvent(dto, publisher);
    applicationEventPublisher.publishEvent(event);
  }

  @Override
  public void requestedSignUpEvent(MemberInfoDto memberInfoDto, LocalDateTime requestedAt) {
    log.info(String.format(
        "[MemberEventAdapter/requestedSignUpEvent]: 회원가입 요청 완료 {memberInfoDto: %s | requestedAt: %s}",
        memberInfoDto, requestedAt
    ));
    var event = new RequestedSignUpEvent(memberInfoDto, requestedAt);
    applicationEventPublisher.publishEvent(event);
  }

  @Override
  public void signedUpEvent(Long memberId, String memberName, String memberUuid,
      Long membershipId) {
    log.info(
        String.format(
            "[MemberEventAdapter/signedUpEvent]: 회원가입 완료 {memberId: %s, memberUuid: %s, membershipId: %s}",
            memberId, memberUuid, membershipId));
    var event = new SignedUpEvent(memberId, memberName, memberUuid, membershipId);
    applicationEventPublisher.publishEvent(event);
  }

  @Override
  public void memberInfoChangedEvent(MemberInfoDto before, MemberInfoDto after) {
    log.info(String.format(
        "[MemberEventAdapter/memberInfoChangedEvent]: 회원 정보 변경 완료 {before: %s, after: %s}",
        before, after));
    // TODO: set event and publish
  }
}
