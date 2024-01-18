package com.pellto.youtoy.global.event.membership;

import com.pellto.youtoy.global.dto.member.MemberInfoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class PublishedInitMembershipEvent {

  private Long membershipId;
  private MemberInfoDto memberInfo;
}
