package com.pellto.youtoy.domain.user.repository;

import static com.pellto.youtoy.domain.user.util.UserUtil.createTestBeforeSavedUser;

import com.pellto.youtoy.domain.user.domain.User;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Tag("repository")
@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @DisplayName("[userRepository: save: success] 저장 잘 되는지 테스트")
  @Test
  void saveTest() {
    User user = createTestBeforeSavedUser();

    User savedUser = userRepository.save(user);

    Assertions.assertThat(savedUser.getId()).isNotNull();
    Assertions.assertThat(savedUser.getUserInfo()).isEqualTo(user.getUserInfo());
    Assertions.assertThat(savedUser.getUuid()).isEqualTo(user.getUuid());
  }

  @DisplayName("[userRepository: findById: success] id 조건 조회 잘 되는지 테스트")
  @Test
  void findByIdTest() {
    var user = createTestBeforeSavedUser();
    user = userRepository.save(user);

    Optional<User> nullableUser = userRepository.findById(user.getId());

    Assertions.assertThat(nullableUser).isNotEmpty();
    User savedUser = nullableUser.get();
    Assertions.assertThat(savedUser).isEqualTo(user);
  }

  @DisplayName("[userRepository: findByUuid: success] UUID 조건 조회 잘 되는지 테스트")
  @Test
  void findByUuidTest() {
    var user = createTestBeforeSavedUser();
    user = userRepository.save(user);

    Optional<User> nullableUser = userRepository.findByUuid(user.getUuid());

    Assertions.assertThat(nullableUser).isNotEmpty();
    User savedUser = nullableUser.get();
    Assertions.assertThat(savedUser).isEqualTo(user);
  }

  @DisplayName("[userRepository: findAll: success] 전체 조회 잘 되는지 테스트")
  @Test
  void findAllTest() {
    var user = createTestBeforeSavedUser();
    userRepository.save(user);

    List<User> savedUsers = userRepository.findAll();

    Assertions.assertThat(savedUsers.size()).isEqualTo(1);
  }
}