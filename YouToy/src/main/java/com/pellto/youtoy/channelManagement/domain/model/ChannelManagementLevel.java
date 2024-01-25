package com.pellto.youtoy.channelManagement.domain.model;

import lombok.Getter;

@Getter
public enum ChannelManagementLevel {
  VIEWER("VIEWER"), CO_WORKER("CO-WORKER"), OWNER("OWNER");
  private final String level;

  ChannelManagementLevel(String level) {
    this.level = level;
  }

  public static ChannelManagementLevel fromString(String level) {
    switch (level) {
      case ("VIEWER") -> {
        return ChannelManagementLevel.VIEWER;
      }
      case ("CO-WORKER") -> {
        return ChannelManagementLevel.CO_WORKER;
      }
      case ("OWNER") -> {
        return ChannelManagementLevel.OWNER;
      }
      default -> {
        throw new IllegalArgumentException();
      }
    }
  }
}
