package com.pellto.youtoy.membership.application.adapter.in.http;

import com.pellto.youtoy.global.dto.membership.MembershipDto;
import com.pellto.youtoy.membership.domain.port.in.GetMembershipUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/memberships")
@RequiredArgsConstructor
public class MembershipController {

  private final GetMembershipUsecase getMembershipUsecase;

  @GetMapping("/{id}")
  public MembershipDto getMembershipInfo(@PathVariable Long id) {
    return getMembershipUsecase.getMembershipInfo(id);
  }
}
