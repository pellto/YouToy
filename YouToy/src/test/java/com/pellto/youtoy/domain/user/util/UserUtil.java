package com.pellto.youtoy.domain.user.util;

import com.pellto.youtoy.domain.user.domain.PremiumLevel;
import com.pellto.youtoy.domain.user.domain.User;
import com.pellto.youtoy.domain.user.dto.UserDto;
import com.pellto.youtoy.domain.user.dto.UserSignUpRequest;
import java.time.LocalDateTime;


public class UserUtil {

  private static final Long ID = 1L;
  private static final String EMAIL = "testEmail";
  private static final LocalDateTime BIRTH_DATE = LocalDateTime.now();
  private static final String PWD = "testPwd";
  private static final String NAME = "testName";
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();
  private static final PremiumLevel PREMIUM_LEVEL = PremiumLevel.NORMAL;

  public static User createTestBeforeSavedUser() {
    return User.builder()
        .email(EMAIL)
        .birthDate(BIRTH_DATE)
        .pwd(PWD)
        .name(NAME)
        .build();
  }

  public static User createTestUser() {
    return User.builder()
        .id(ID)
        .email(EMAIL)
        .createdAt(CREATED_AT)
        .birthDate(BIRTH_DATE)
        .pwd(PWD)
        .name(NAME)
        .premiumLevel(PREMIUM_LEVEL)
        .build();
  }

  public static User createTestUser(Long id) {
    return User.builder()
        .id(id)
        .email(EMAIL)
        .createdAt(CREATED_AT)
        .birthDate(BIRTH_DATE)
        .pwd(PWD)
        .name(NAME)
        .premiumLevel(PREMIUM_LEVEL)
        .build();
  }

  public static UserDto createTestUserDto() {
    return new UserDto(
        ID,
        EMAIL,
        NAME,
        PREMIUM_LEVEL,
        BIRTH_DATE,
        CREATED_AT
    );
  }

  public static UserDto createTestUserDto(Long id) {
    return new UserDto(
        id,
        EMAIL,
        NAME,
        PREMIUM_LEVEL,
        BIRTH_DATE,
        CREATED_AT
    );
  }

  public static UserSignUpRequest createSignUpRequest() {
    return new UserSignUpRequest(
        EMAIL,
        BIRTH_DATE,
        PWD,
        PWD,
        NAME
    );
  }
}
