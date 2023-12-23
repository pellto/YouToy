package com.pellto.youtoy.domain.user.util;

import com.pellto.youtoy.domain.user.domain.PremiumLevel;
import com.pellto.youtoy.domain.user.domain.User;
import com.pellto.youtoy.domain.user.domain.UserInfo;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import com.pellto.youtoy.domain.user.dto.UserDto;
import com.pellto.youtoy.domain.user.dto.UserSignUpRequest;
import com.pellto.youtoy.global.util.RandomString;
import java.time.LocalDateTime;


public class UserUtil {

  private static final Long ID = 1L;
  private static final UserUUID USER_UUID = new UserUUID(RandomString.make());
  private static final UserInfo USER_INFO = new UserInfo(
      "test@Email.email",
      "testPwd",
      "testName",
      LocalDateTime.now()
  );
  private static final String REPEAT_PWD = "testPwd";
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();
  private static final PremiumLevel PREMIUM_LEVEL = PremiumLevel.NORMAL;

  public static User createTestBeforeSavedUser() {
    return User.builder()
        .userInfo(USER_INFO)
        .build();
  }

  public static User createTestUser() {
    return User.builder()
        .id(ID)
        .userInfo(USER_INFO)
        .premiumLevel(PREMIUM_LEVEL)
        .createdAt(CREATED_AT)
        .build();
  }

  public static User createTestUser(Long id) {
    return User.builder()
        .id(id)
        .userInfo(USER_INFO)
        .createdAt(CREATED_AT)
        .premiumLevel(PREMIUM_LEVEL)
        .build();
  }

  public static UserDto createTestUserDto() {
    return new UserDto(
        USER_UUID,
        USER_INFO,
        PREMIUM_LEVEL,
        CREATED_AT
    );
  }

  public static UserSignUpRequest createSignUpRequest() {
    return new UserSignUpRequest(
        USER_INFO,
        REPEAT_PWD
    );
  }
}
