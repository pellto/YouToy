package com.pellto.youtoy.global.util;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomString {

  private static final int _INIT_LENGTH = 10;
  private static final boolean _INIT_USE_LETTERS = true;
  private static final boolean _INIT_USE_NUMBERS = true;

  public static String make() {
    return make(_INIT_LENGTH, _INIT_USE_LETTERS, _INIT_USE_NUMBERS);
  }

  public static String make(int length, boolean useLetters, boolean useNumbers) {
    return RandomStringUtils.random(length, useLetters, useNumbers);
  }
}
