package com.pellto.youtoy.util.user;

import com.pellto.youtoy.domain.user.dto.UpdateUserInfoCommand;
import java.time.LocalDate;

public class UpdateUserCommandFixtureFactory {

  private static final Long ID = 1L;
  private static final String EMAIL = "changedEmail@email.com";
  private static final String PWD = "changedPwd";
  private static final String REPEAT_PWD = "changedPwd";
  private static final String NAME = "changedName";
  private static final LocalDate BIRTH_DATE = LocalDate.of(2023, 1, 2);

  public static UpdateUserInfoCommand get() {
    return get(ID, EMAIL, PWD, REPEAT_PWD, NAME, BIRTH_DATE);
  }

  public static UpdateUserInfoCommand get(
      Long id,
      String email,
      String pwd,
      String repeatPwd,
      String name,
      LocalDate birthDate
  ) {
    return new UpdateUserInfoCommand(id, email, pwd, repeatPwd, name, birthDate);
  }
}
