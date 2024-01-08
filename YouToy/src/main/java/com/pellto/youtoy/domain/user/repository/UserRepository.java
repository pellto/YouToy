package com.pellto.youtoy.domain.user.repository;

import com.pellto.youtoy.domain.user.domain.User;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

  @Query(value = "SELECT user FROM User user WHERE user.userInfo.email = :email")
  Optional<User> findByEmail(@Param("email") String email);

  Optional<User> findByUuid(UserUUID uuid);
}
