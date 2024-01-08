package com.pellto.youtoy.domain.user.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.domain.user.repository.UserRepository;
import com.pellto.youtoy.domain.user.util.UserUtil;
import com.pellto.youtoy.global.security.TokenProvider;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@Tag("service")
@ExtendWith(MockitoExtension.class)
class UserWriteServiceTest {

  @InjectMocks
  private UserWriteService userWriteService;
  @Mock
  private UserRepository userRepository;
  @Mock
  private PasswordEncoder passwordEncoder;
  @Mock
  private UserReadService userReadService;
  @Mock
  private TokenProvider tokenProvider;

  @DisplayName("[userWriteService: signUp: success] 유저 회원 가입 성공 테스트")
  @Test
  void signUpSuccessTest() {
    var req = UserUtil.createSignUpRequest();
    var user = UserUtil.createTestUser();
    var userDto = UserUtil.createTestUserDto();

    given(userRepository.save(any())).willReturn(user);
    given(userReadService.toDto(user)).willReturn(userDto);

    var savedUser = userWriteService.signUp(req);

    then(userRepository).should(times(1)).save(any());
    then(userReadService).should(times(1)).toDto(user);
    Assertions.assertThat(savedUser).isNotNull();
    Assertions.assertThat(savedUser).isEqualTo(userDto);
  }

  @DisplayName("[userWriteService: signIn: success] 유저 로그인 성공 테스트")
  @Test
  void signIpSuccessTest() {
    var req = UserUtil.createSignInRequest();
    var user = UserUtil.createTestUser();
    var TEST_TOKEN = "TEST_TOKEN";

    given(userRepository.findByEmail(any())).willReturn(Optional.ofNullable(user));
    given(passwordEncoder.matches(any(), any())).willReturn(true);
    given(tokenProvider.createToken(any())).willReturn(TEST_TOKEN);

    var signInResponse = userWriteService.signIn(req);

    then(userRepository).should(times(1)).findByEmail(any());
    then(passwordEncoder).should(times(1)).matches(any(), any());
    then(tokenProvider).should(times(1)).createToken(any());
    Assertions.assertThat(signInResponse).isNotNull();
    Assertions.assertThat(signInResponse.name()).isEqualTo(user.getUserInfo().getName());
    Assertions.assertThat(signInResponse.type()).isEqualTo(user.getMemberType());
    Assertions.assertThat(signInResponse.token()).isEqualTo(TEST_TOKEN);
  }
}