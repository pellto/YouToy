package com.pellto.youtoy.util.user;

import com.pellto.youtoy.domain.user.dto.RegisterUserCommand;
import com.pellto.youtoy.domain.user.entity.User;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public class UserFixtureFactory {
    static public User create() {
        var params = new EasyRandomParameters();
        return new EasyRandom(params).nextObject(User.class);
    }

    static public User create(RegisterUserCommand cmd) {
        return User.builder()
                .email(cmd.email())
                .pwd(cmd.pwd())
                .name(cmd.name())
                .birthDate(cmd.birthDate())
                .build();

    }
}
