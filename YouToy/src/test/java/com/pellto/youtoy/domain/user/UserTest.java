package com.pellto.youtoy.domain.user;

import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.pellto.youtoy.domain.user.entity.User;
import com.pellto.youtoy.domain.user.repository.UserRepository;
import com.pellto.youtoy.domain.user.service.UserWriteService;
import com.pellto.youtoy.util.RegisterUserCommandFixtureFactory;
import com.pellto.youtoy.util.UserFixtureFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.Optional;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
public class UserTest {
    @InjectMocks
    private UserWriteService userWriteService;
    @Mock
    private UserRepository userRepository;
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
    }
}
