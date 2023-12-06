package com.pellto.youtoy.domain.user;

import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.pellto.youtoy.domain.user.repository.UserRepository;
import com.pellto.youtoy.domain.user.service.UserReadService;
import com.pellto.youtoy.domain.user.service.UserWriteService;
import com.pellto.youtoy.util.user.RegisterUserCommandFixtureFactory;
import com.pellto.youtoy.util.user.UpdateUserCommandFixtureFactory;
import com.pellto.youtoy.util.user.UserFixtureFactory;
import com.pellto.youtoy.util.error.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

@Tag("domain")
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserWriteService userWriteService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserReadService userReadService;

    @DisplayName("[User: register] 유저 생성 테스트")
    @Test
    public void createUserTest() {
        // given
        var cmd = RegisterUserCommandFixtureFactory.get();
        var user = UserFixtureFactory.create(cmd);

        // mocking
        given(userRepository.findById(any())).willReturn(Optional.ofNullable(user));

        // when
        userWriteService.register(cmd);

        // then
        assert user != null;
        var findUser = userRepository.findById(user.getId()).orElseThrow();

        assertEquals(findUser.getId(), user.getId());
        assertEquals(findUser.getPwd(), user.getPwd());
        assertEquals(findUser.getName(), user.getName());
        assertEquals(findUser.getBirthDate(), user.getBirthDate());
        assertEquals(findUser.getCreatedAt(), user.getCreatedAt());
        then(userReadService).should(times(1)).toDto(any());
    }

    @DisplayName("[User: register: wrong password] 유저 생성시 다른 비밀번호 테스트")
    @Test
    public void createUserWrongPwdTest() {
        // given
        String wrongPwd = "wrongPwd";
        var cmd = RegisterUserCommandFixtureFactory.getWithPwd(wrongPwd);

        try {
            // when
            userWriteService.register(cmd);
        } catch (Exception e) {
            // then
            assertEquals(e.getMessage(), ErrorCode.PASSWORD_MISMATCH.getMessage());
        }
    }

    @DisplayName("[User: register: already exist email] 유저 생성시 이메일 중복 테스트")
    @Test
    public void createUserExistEmailTest() {
        // given
        var cmd = RegisterUserCommandFixtureFactory.get();

        // mocking
        given(userRepository.existsEmail(cmd.email())).willReturn(true);

        try {
            // when
            userWriteService.register(cmd);
        } catch (Exception e) {
            // then
            assertEquals(e.getMessage(), ErrorCode.ALREADY_EXIST_EMAIL.getMessage());
        }
        then(userRepository).should(times(1)).existsEmail(any());
    }

    @DisplayName("[User: update: success] 유저 정보 변경 테스트")
    @Test
    public void updateUserTest() {
        // given
        var user = UserFixtureFactory.create(RegisterUserCommandFixtureFactory.get());
        var cmd = UpdateUserCommandFixtureFactory.get();

        // mocking
        given(userRepository.findById(any())).willReturn(Optional.ofNullable(user));

        // when
        userWriteService.update(cmd);

        // then
        assertNotNull(user);
        assertEquals(cmd.email(), user.getEmail());
        assertEquals(cmd.pwd(), user.getPwd());
        assertEquals(cmd.name(), user.getName());
        assertEquals(cmd.birthDate(), user.getBirthDate());
        then(userRepository).should(times(1)).findById(any());
        then(userRepository).should(times(1)).save(user);
    }

    @DisplayName("[User: update: nullUser] 없는 유저 정보 변경 테스트")
    @Test
    public void updateNullUserTest() {
        // given
        var cmd = UpdateUserCommandFixtureFactory.get();

        // when & then
        assertThrows(
                NoSuchElementException.class,
                () -> userWriteService.update(cmd)
        );
        then(userRepository).should(times(1)).findById(any());
    }

    @DisplayName("[User: update: unchanged-Info] 변경된 정보 없는 유저 정보 변경 테스트")
    @Test
    public void updateUnchangedUserTest() {
        // given
        var user = UserFixtureFactory.create(RegisterUserCommandFixtureFactory.get());
        var cmd = UpdateUserCommandFixtureFactory.get(
                user.getId(),
                null,
                null,
                null,
                null,
                null
        );

        // mocking
        given(userRepository.findById(any())).willReturn(Optional.of(user));

        // when
        userWriteService.update(cmd);

        // then
        then(userRepository).should(times(1)).findById(any());
        then(userRepository).should(times(0)).save(any());
    }

    @DisplayName("[User: update: wrong password] 유저 정보 변경시 다른 비밀번호 테스트")
    @Test
    public void updateUserWrongPwdTest() {
        // given
        var user = UserFixtureFactory.create(RegisterUserCommandFixtureFactory.get());
        var cmd = UpdateUserCommandFixtureFactory.get(
                null,
                null,
                "pwd",
                "wrongPwd",
                null,
                null
        );

        // mocking
        given(userRepository.findById(any())).willReturn(Optional.of(user));

        try {
            // when
            userWriteService.update(cmd);
        } catch (Exception e) {
            // then
            assertEquals(e.getMessage(), ErrorCode.PASSWORD_MISMATCH.getMessage());
        }

        then(userRepository).should(times(1)).findById(any());
        then(userRepository).should(times(0)).save(any());
    }
}
