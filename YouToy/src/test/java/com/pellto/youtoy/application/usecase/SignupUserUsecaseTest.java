package com.pellto.youtoy.application.usecase;

import com.pellto.youtoy.domain.channel.service.ChannelWriteService;
import com.pellto.youtoy.domain.user.service.UserWriteService;
import com.pellto.youtoy.util.user.RegisterUserCommandFixtureFactory;
import com.pellto.youtoy.util.user.UserFixtureFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.*;

@Tag("usecase")
@ExtendWith(MockitoExtension.class)
@DisplayName("[SignupUserUsecase Test]")
public class SignupUserUsecaseTest {
    @InjectMocks
    private SignupUserUsecase signupUserUsecase;
    @Mock
    private UserWriteService userWriteService;
    @Mock
    private ChannelWriteService channelWriteService;

    @DisplayName("[execute: success] 유저 회원가입 성공 테스트")
    @Test
    public void executeTest() {
        var cmd = RegisterUserCommandFixtureFactory.get();
        var userDto = UserFixtureFactory.toDto(UserFixtureFactory.create(cmd));

        given(userWriteService.register(cmd)).willReturn(userDto);

        var registeredUser = signupUserUsecase.execute(cmd);

        assertEquals(cmd.name(), registeredUser.name());
        assertEquals(cmd.email(), registeredUser.email());
        assertEquals(cmd.birthDate(), registeredUser.birthDate());
        assertEquals(cmd.pwd(), registeredUser.pwd());
        then(userWriteService).should(times(1)).register(cmd);
        then(channelWriteService).should(times(1)).create(any());
    }
}
