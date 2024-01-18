package com.pellto.youtoy.membership.domain.Service;

import com.pellto.youtoy.global.dto.member.MemberInfoDto;
import com.pellto.youtoy.membership.domain.model.Membership;
import com.pellto.youtoy.membership.domain.port.in.PublishInitMembershipUsecase;
import com.pellto.youtoy.membership.domain.port.out.MembershipEventPort;
import com.pellto.youtoy.membership.domain.port.out.SaveMembershipPort;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipPublishService implements PublishInitMembershipUsecase {

  private final MembershipEventPort membershipEventPort;
  private final SaveMembershipPort saveMembershipPort;


  @Override
  public void publish(MemberInfoDto memberInfoDto, LocalDateTime requiredAt) {

    var membership = Membership.builder()
        .email(memberInfoDto.email())
        .startedAt(requiredAt)
        .build();
    membership = saveMembershipPort.save(membership);

    membershipEventPort.publishedInitMembership(membership.getId(), memberInfoDto);

  }
}
