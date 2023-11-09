package com.pellto.youtoy.domain.user.service;

import com.pellto.youtoy.domain.user.dto.LoginUserCommand;
import com.pellto.youtoy.domain.user.dto.UserDto;
import com.pellto.youtoy.domain.user.entity.User;
import com.pellto.youtoy.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserReadService {
    private final UserRepository userRepository;

    public UserDto login(LoginUserCommand cmd) {
        var user = userRepository.findByEmail(cmd.email()).orElseThrow();

        if (!Objects.equals(user.getPwd(), cmd.pwd())) {
            throw new UnsupportedOperationException("패스워드가 잘못되었습니다.");
        }
        return toDto(user);
    }

    public UserDto getUser(Long id) {
        var user = userRepository.findById(id).orElseThrow();
        return toDto(user);
    }

    public void logout(String email) {
        // TODO: check user's token in DB
        System.out.println(email + " 이 로그아웃 되었습니다.");
    }

    public UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getPwd(),
                user.getName(),
                user.getBirthDate()
        );
    }
}
