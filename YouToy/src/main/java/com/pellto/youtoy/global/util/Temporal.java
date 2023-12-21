package com.pellto.youtoy.global.util;

import java.time.LocalDateTime;

public class Temporal {

  public static LocalDateTime createdAt(LocalDateTime createdAt) {
    return General.setNullInput(createdAt, LocalDateTime.now());
  }
}
