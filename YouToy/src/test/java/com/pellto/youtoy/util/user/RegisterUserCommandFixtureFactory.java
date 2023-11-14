package com.pellto.youtoy.util.user;

import com.pellto.youtoy.domain.user.dto.RegisterUserCommand;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RegisterUserCommandFixtureFactory {
    private static final String EMAIL = "email@email.com";
    private static final String PWD = "pwd";
    private static final String REPEAT_PWD = "pwd";
    private static final String NAME = "name";
    private static final LocalDate BIRTH_DATE = LocalDate.of(
            2023, 1, 1
    );

    public static RegisterUserCommand create() {
        var params = new EasyRandomParameters();
        return new EasyRandom(params).nextObject(RegisterUserCommand.class);
    }

    public static RegisterUserCommand get() {
        return new RegisterUserCommand(EMAIL, PWD, REPEAT_PWD, NAME, BIRTH_DATE);
    }

    public static RegisterUserCommand getWithPwd(String pwd) {
        return new RegisterUserCommand(EMAIL, pwd, REPEAT_PWD, NAME, BIRTH_DATE);
    }
}
