package com.pellto.youtoy.global.util;

import java.util.Arrays;

public class Enums {

  public static String[] getEnumNames(Class<? extends Enum<?>> e) {
    return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
  }
}
