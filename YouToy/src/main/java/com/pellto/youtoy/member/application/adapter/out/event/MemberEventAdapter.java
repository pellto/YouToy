package com.pellto.youtoy.member.application.adapter.out.event;

import com.pellto.youtoy.global.dto.member.MemberInfoDto;
import com.pellto.youtoy.global.event.member.RequestedSignUpEvent;
import com.pellto.youtoy.global.event.member.SignedUpEvent;
import com.pellto.youtoy.member.domain.port.out.MemberEventPort;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MemberEventAdapter implements MemberEventPort {

  private final ApplicationEventPublisher applicationEventPublisher;

  @Override
  public void requestedSignUpEvent(MemberInfoDto memberInfoDto, LocalDateTime requiredAt) {
    log.info(String.format(
        "[MemberEventAdapter/requestedSignUpEvent]: 회원가입 요청 완료 {memberInfoDto: %s | requiredAt: %s}",
        memberInfoDto, requiredAt
    ));
    var event = new RequestedSignUpEvent(memberInfoDto, requiredAt);
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
}
