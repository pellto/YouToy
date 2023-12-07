package com.pellto.youtoy.util.user;

import com.pellto.youtoy.domain.user.dto.RegisterUserCommand;
import com.pellto.youtoy.domain.user.dto.UserDto;
import com.pellto.youtoy.domain.user.entity.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public class UserFixtureFactory {

  private static final Long ID = 1L;
  private static final String EMAIL = "email";
  private static final String PWD = "pwd";
  private static final String NAME = "name";
  private static final LocalDate BIRTH_DATE = LocalDate.of(
      2023, 1, 1
  );
  private static final LocalDateTime CREATED_AT = LocalDateTime.of(
      2023, 1, 1, 0, 0, 0
  );

  public static User create() {
    return create(ID, EMAIL, PWD, NAME, BIRTH_DATE, CREATED_AT);
  }

  public static User create(
      Long id,
      String email,
      String pwd,
      String name,
      LocalDate birthDate,
      LocalDateTime createdAt
  ) {
    return User.builder()
        .id(id)
        .email(email)
        .pwd(pwd)
        .name(name)
        .birthDate(birthDate)
        .createdAt(createdAt)
        .build();
  }

  public static User create(RegisterUserCommand cmd) {
    return create(ID, cmd.email(), cmd.pwd(), cmd.name(), cmd.birthDate(), CREATED_AT);
  }

  public static User createRandom() {
    var params = new EasyRandomParameters();
    return new EasyRandom(params).nextObject(User.class);
  }

  public static UserDto toDto(User user) {
    return new UserDto(
        user.getId(),
        user.getEmail(),
        user.getPwd(),
        user.getName(),
        user.getBirthDate()
    );
  }
}
