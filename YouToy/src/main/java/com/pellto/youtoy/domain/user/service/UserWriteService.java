package com.pellto.youtoy.domain.user.service;

import com.pellto.youtoy.domain.user.dto.RegisterUserCommand;
import com.pellto.youtoy.domain.user.dto.UserDto;
import com.pellto.youtoy.domain.user.entity.User;
import com.pellto.youtoy.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserWriteService {
    private final UserRepository userRepository;

    public User register(RegisterUserCommand cmd) {
        if (!Objects.equals(cmd.pwd(), cmd.repeatPwd())) {
            // TODO: convert custom error
            throw new UnsupportedOperationException("비밀번호가 일치 하지 않습니다.");
        }
        if (userRepository.existsEmail(cmd.email())) {
            // TODO: convert custom error
            throw new UnsupportedOperationException("이미 존재하는 email 입니다.");
        }

        var user = User.builder()
                .email(cmd.email())
                .pwd(cmd.pwd())
                .name(cmd.name())
                .birthDate(cmd.birthDate())
                .build();

        return userRepository.save(user);
    }
}
