package com.pellto.youtoy.global.dto.auth;

import java.util.List;

public record AuthDto(
    String email,
    List<String> channelRoles,
    List<String> memberRoles,
    String token

) {

  public String getAuthInfoString() {
    return String.format("^%s#%s#%s^", email, channelRoles.toString(), memberRoles.toString());
  }
}
