package com.pellto.youtoy.domain.user.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.domain.user.domain.User;
import com.pellto.youtoy.domain.user.dto.UserDto;
import com.pellto.youtoy.domain.user.repository.UserRepository;
import com.pellto.youtoy.domain.user.util.UserUtil;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@Tag("service")
@ExtendWith(MockitoExtension.class)
class UserReadServiceTest {

  @InjectMocks
  private UserReadService userReadService;

  @Mock
  private UserRepository userRepository;

  @DisplayName("[userReadService: findAll: success] 유저 전체 찾기 테스트")
  @Test
  void findAllSuccess() {
    var user = UserUtil.createTestUser();
    var userList = new ArrayList<User>();
    userList.add(user);

    given(userRepository.findAll()).willReturn(userList);

    var foundUsers = userReadService.findAll();

    then(userRepository).should(times(1)).findAll();
    Assertions.assertNotNull(foundUsers);
    Assertions.assertEquals(1, foundUsers.size());
    Assertions.assertEquals(UserDto.class, foundUsers.get(0).getClass());
  }

  @DisplayName("[userReadService: findById: success] 유저 id 조건 찾기 테스트")
  @Test
  void findByIdSuccess() {
    var userId = 1L;
    var user = UserUtil.createTestUser(userId);

    given(userRepository.findById(any())).willReturn(Optional.ofNullable(user));

    var foundUser = userReadService.findById(userId);

    then(userRepository).should(times(1)).findById(userId);
    Assertions.assertNotNull(foundUser);
    Assertions.assertEquals(user.getUuid(), foundUser.uuid());
  }

  @DisplayName("[userReadService: findByUserUuid: success] 유저 UUID 조건 찾기 테스트")
  @Test
  void findByUserUuidSuccess() {
    var user = UserUtil.createTestUser();
    var userUuid = user.getUuid().getValue();

    given(userRepository.findByUuid(any())).willReturn(Optional.ofNullable(user));

    var foundUser = userReadService.findByUserUuid(userUuid);

    then(userRepository).should(times(1)).findByUuid(any());
    Assertions.assertNotNull(foundUser);
    Assertions.assertEquals(user.getUuid(), foundUser.uuid());
  }
}