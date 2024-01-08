package com.pellto.youtoy.domain.user.application;

import com.pellto.youtoy.domain.user.domain.User;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import com.pellto.youtoy.domain.user.dto.UserDto;
import com.pellto.youtoy.domain.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserReadService {

  private final UserRepository userRepository;

  public List<UserDto> findAll() {
    return userRepository.findAll().stream().map(this::toDto).toList();
  }

  public UserDto findById(Long id) {
    return toDto(userRepository.findById(id).orElseThrow());
  }

  public UserDto findByUserUuid(String uuid) {
    return toDto(userRepository.findByUuid(new UserUUID(uuid)).orElseThrow());
  }

  public UserDto toDto(User user) {
    return new UserDto(
        user.getUuid(),
        user.getUserInfo(),
        user.getPremiumLevel(),
        user.getCreatedAt()
    );
  }
}
