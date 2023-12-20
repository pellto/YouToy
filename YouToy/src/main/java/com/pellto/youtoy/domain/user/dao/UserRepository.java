package com.pellto.youtoy.domain.user.dao;

import com.pellto.youtoy.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
