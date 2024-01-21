package com.pellto.youtoy.membership.domain.Service;

import com.pellto.youtoy.global.dto.member.MemberDto;
import com.pellto.youtoy.global.dto.member.MemberInfoDto;
import com.pellto.youtoy.membership.domain.model.Membership;
import com.pellto.youtoy.membership.domain.port.in.PublishInitMembershipUsecase;
import com.pellto.youtoy.membership.domain.port.in.RemoveMembershipUsecase;
import com.pellto.youtoy.membership.domain.port.out.LoadMembershipPort;
import com.pellto.youtoy.membership.domain.port.out.MembershipEventPort;
import com.pellto.youtoy.membership.domain.port.out.SaveMembershipPort;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipPublishService implements PublishInitMembershipUsecase,
    RemoveMembershipUsecase {

  private final MembershipEventPort membershipEventPort;
  private final SaveMembershipPort saveMembershipPort;
  private final LoadMembershipPort loadMembershipPort;


  @Override
  public void publish(MemberInfoDto memberInfoDto, LocalDateTime requiredAt) {

    var membership = Membership.builder()
        .email(memberInfoDto.email())
        .startedAt(requiredAt)
        .build();
    membership = saveMembershipPort.save(membership);

    membershipEventPort.publishedInitMembership(membership.getId(), memberInfoDto);

  }

  @Override
  public void remove(MemberDto dto) {
    var membership = loadMembershipPort.load(dto.membershipId());
    
    saveMembershipPort.delete(membership);

    membershipEventPort.membershipRemovedEvent(membership.toDto());
  }
}
