package com.pellto.youtoy.global.dto.auth;

import java.util.List;

public record AuthDto(
    Long memberId,
    String email,
    List<String> channelRoles,
    List<String> memberRoles,
    String token

) {

  public String getAuthInfoString() {
    return String.format("^%s[%s]#%s#%s^", email, memberId, channelRoles.toString(),
        memberRoles.toString());
  }
}
