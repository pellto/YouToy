package com.pellto.youtoy.domain.user.service;

import com.pellto.youtoy.domain.user.dto.RegisterUserCommand;
import com.pellto.youtoy.domain.user.dto.UpdateUserInfoCommand;
import com.pellto.youtoy.domain.user.dto.UserDto;
import com.pellto.youtoy.domain.user.entity.User;
import com.pellto.youtoy.domain.user.repository.UserRepository;
import com.pellto.youtoy.util.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserWriteService {
    private final UserRepository userRepository;
    private final UserReadService userReadService;

    public UserDto register(RegisterUserCommand cmd) {
        if (!cmd.pwd().equals(cmd.repeatPwd())) {
            // TODO: convert custom error
            throw new UnsupportedOperationException(ErrorCode.PASSWORD_MISMATCH.getMessage());
        }
        if (userRepository.existsEmail(cmd.email())) {
            // TODO: convert custom error
            throw new UnsupportedOperationException(ErrorCode.ALREADY_EXIST_EMAIL.getMessage());
        }

        var user = User.builder()
                .email(cmd.email())
                .pwd(cmd.pwd())
                .name(cmd.name())
                .birthDate(cmd.birthDate())
                .build();

        return userReadService.toDto(userRepository.save(user));
    }

    public void update(UpdateUserInfoCommand cmd) {
        User user = userRepository.findById(cmd.id()).orElseThrow();
        boolean changeChecker = false;
        if (cmd.email() != null) {
            user.setEmail(cmd.email());
            changeChecker = true;
        }
        if (cmd.pwd() != null
                && cmd.repeatPwd() != null) {
            if (!cmd.pwd().equals(cmd.repeatPwd())) {
                throw new UnsupportedOperationException(ErrorCode.PASSWORD_MISMATCH.getMessage());
            }
            user.setPwd(cmd.pwd());
            changeChecker = true;
        }
        if (cmd.name() != null) {
            user.setName(cmd.name());
            changeChecker = true;
        }
        if (cmd.birthDate() != null) {
            user.setBirthDate(cmd.birthDate());
            changeChecker = true;
        }
        if (changeChecker) userRepository.save(user);
    }
}
