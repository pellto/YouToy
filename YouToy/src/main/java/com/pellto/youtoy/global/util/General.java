package com.pellto.youtoy.global.util;

public class General {

  public static <T> T setNullInput(T input, T initValue) {
    return input == null ? initValue : input;
  }
}
