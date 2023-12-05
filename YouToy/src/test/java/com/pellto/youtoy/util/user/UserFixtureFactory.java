package com.pellto.youtoy.util.user;

import com.pellto.youtoy.domain.user.dto.RegisterUserCommand;
import com.pellto.youtoy.domain.user.dto.UserDto;
import com.pellto.youtoy.domain.user.entity.User;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public class UserFixtureFactory {
    public static User create() {
        var params = new EasyRandomParameters();
        return new EasyRandom(params).nextObject(User.class);
    }

    public static User create(RegisterUserCommand cmd) {
        return User.builder()
                .email(cmd.email())
                .pwd(cmd.pwd())
                .name(cmd.name())
                .birthDate(cmd.birthDate())
                .build();

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
