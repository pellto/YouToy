package com.pellto.youtoy.auth.domain.model;

import com.pellto.youtoy.channelManagement.domain.model.ChannelManagementLevel;
import com.pellto.youtoy.global.dto.channelManagement.ChannelManagementDto;
import com.pellto.youtoy.global.util.Enums;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ChannelRoles {

  private static final List<String> CHANNEL_LEVELS = Arrays.asList(Enums.getEnumNames(
      ChannelManagementLevel.class));

  private final List<String> roles;

  @Builder
  public ChannelRoles(List<String> roles) {
    this.roles = roles;
    this.validate();
  }

  private void validate() {
    if (roles.isEmpty()) {
      throw new IllegalArgumentException("올바르지 않은 채널 권한 입니다.");
    }
    roles.forEach(role -> {
      if (!validateRole(role)) {
        throw new IllegalArgumentException("올바르지 않은 권한입니다.");
      }
    });
  }

  private boolean validateRole(String role) {
    if (!role.contains("$")) {
      throw new IllegalArgumentException("올바르지 않은 role 입니다.");
    }

    var substrings = role.split("\\$");
    if (substrings.length != 2) {
      throw new IllegalArgumentException("올바르지 않은 role 입니다.2");
    }

    if (!CHANNEL_LEVELS.contains(substrings[1])) {
      throw new IllegalArgumentException("올바르지 않은 권한입니다.");
    }
    
    return true;
  }

  public static ChannelRoles fromManagement(List<ChannelManagementDto> dtos) {
    var _roles = new ArrayList<String>();
    dtos.forEach(dto -> _roles.add(String.format("%s$%s", dto.channelId(), dto.manageLevel())));
    return ChannelRoles.builder()
        .roles(_roles.stream().toList())
        .build();
  }
}

