package com.pellto.youtoy.auth.domain.model;

import com.pellto.youtoy.global.dto.member.MemberInfoDto;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberRoles {

  private List<String> roles;

  @Builder
  public MemberRoles(List<String> roles) {
    this.roles = roles;
  }

  public static MemberRoles fromMemberInfo(MemberInfoDto memberInfo) {
    var _roles = new ArrayList<String>();
    _roles.add(memberInfo.name());
    return MemberRoles.builder().roles(_roles).build();
  }
}
