package com.pellto.youtoy.domain.user.application;

import com.pellto.youtoy.domain.user.dao.UserRepository;
import com.pellto.youtoy.domain.user.domain.User;
import com.pellto.youtoy.domain.user.dto.UserDto;
import com.pellto.youtoy.domain.user.dto.UserSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserWriteService {

  private final UserRepository userRepository;
  private final UserReadService userReadService;

  public UserDto signUp(UserSignUpRequest req) {
    if (!req.pwd().equals(req.repeatPwd())) {
      throw new UnsupportedOperationException("비밀번호 틀림");
    }
    var user = User.builder()
        .email(req.email())
        .birthDate(req.birthDate())
        .pwd(req.pwd())
        .name(req.name())
        .build();
    return userReadService.toDto(userRepository.save(user));
  }
}
