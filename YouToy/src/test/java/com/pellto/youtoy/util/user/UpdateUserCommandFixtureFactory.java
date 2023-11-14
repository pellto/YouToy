package com.pellto.youtoy.util.user;

import com.pellto.youtoy.domain.user.dto.UpdateUserInfoCommand;

import java.time.LocalDate;

public class UpdateUserCommandFixtureFactory {
    public static UpdateUserInfoCommand get() {
        Long id = 1L;
        String email = "changedEmail@email.com";
        String pwd = "changedPwd";
        String repeatPwd = "changedPwd";
        String name = "changedName";
        LocalDate birthDate = LocalDate.of(2023, 1, 2);

        return new UpdateUserInfoCommand(id, email, pwd, repeatPwd, name, birthDate);
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
