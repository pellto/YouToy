package com.pellto.youtoy.domain.user.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.domain.user.dao.UserRepository;
import com.pellto.youtoy.domain.user.util.UserUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserWriteServiceTest {

  @InjectMocks
  private UserWriteService userWriteService;
  @Mock
  private UserRepository userRepository;
  @Mock
  private UserReadService userReadService;

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
    Assertions.assertNotNull(savedUser);
    Assertions.assertEquals(userDto, savedUser);
  }
}