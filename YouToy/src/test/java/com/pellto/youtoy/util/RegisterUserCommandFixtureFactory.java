package com.pellto.youtoy.util;

import com.pellto.youtoy.domain.user.dto.RegisterUserCommand;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RegisterUserCommandFixtureFactory {
    public static RegisterUserCommand create() {
        var params = new EasyRandomParameters();
        return new EasyRandom(params).nextObject(RegisterUserCommand.class);
    }

    public static RegisterUserCommand get() {
        String email = "email@email.com";
        String pwd = "pwd";
        String repeatPwd = "pwd";
        String name = "name";
        LocalDate birthDate = LocalDate.of(2023, 1, 1);

        return new RegisterUserCommand(email, pwd, repeatPwd, name, birthDate);
    }

    public static RegisterUserCommand getWithPwd(String pwd) {
        String email = "email@email.com";
        String repeatPwd = "pwd";
        String name = "name";
        LocalDate birthDate = LocalDate.of(2023, 1, 1);

        return new RegisterUserCommand(email, pwd, repeatPwd, name, birthDate);
    }
}
