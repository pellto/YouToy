package com.pellto.youtoy.member.domain.service;

import com.pellto.youtoy.global.dto.member.response.GetMemberInfoResponse;
import com.pellto.youtoy.global.dto.member.response.GetMemberResponse;
import com.pellto.youtoy.member.domain.port.in.GetMemberDetailUsecase;
import com.pellto.youtoy.member.domain.port.out.LoadMemberPort;
import com.pellto.youtoy.member.domain.port.out.MembershipHandlePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetMemberDetailService implements GetMemberDetailUsecase {

  private final LoadMemberPort loadMemberPort;
  private final MembershipHandlePort membershipHandlePort;

  @Override
  public GetMemberResponse getMemberDetail(Long id) {
    var member = loadMemberPort.load(id);
    var membershipDto = membershipHandlePort.getMembershipInfo(member.getMembershipId());

    return new GetMemberResponse(
        member.getId(),
        member.getUuid().value(),
        member.getCreatedAt(),
        member.getMemberInfoDto(),
        membershipDto
    );
  }

  @Override
  public GetMemberInfoResponse getMemberInfoByEmail(String email) {
    var member = loadMemberPort.loadByEmail(email);
    return new GetMemberInfoResponse(
        member.getId(),
        member.getMemberInfo().getEmail(),
        member.getMemberInfo().getPwd(),
        member.getMemberInfo().getName(),
        member.getMemberInfo().getBirthDate()
    );
  }
}
