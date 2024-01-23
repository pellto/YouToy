package com.pellto.youtoy.membership.domain.Service;

import com.pellto.youtoy.global.dto.membership.MembershipDto;
import com.pellto.youtoy.membership.domain.port.in.GetMembershipUsecase;
import com.pellto.youtoy.membership.domain.port.out.LoadMembershipPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GetMembershipService implements GetMembershipUsecase {

  private final LoadMembershipPort loadMembershipPort;

  @Override
  public MembershipDto getMembershipInfo(Long id) {
    return loadMembershipPort.load(id).toDto();
  }
}
