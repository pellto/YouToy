package com.pellto.youtoy.domain.user.application;

import com.pellto.youtoy.domain.user.domain.User;
import com.pellto.youtoy.domain.user.dto.UserDto;
import com.pellto.youtoy.domain.user.dto.UserSignUpRequest;
import com.pellto.youtoy.domain.user.exception.WrongRepeatPasswordException;
import com.pellto.youtoy.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserWriteService {

  private final UserRepository userRepository;
  private final UserReadService userReadService;

  public UserDto signUp(UserSignUpRequest req) {
    if (!req.userInfo().getPwd().equals(req.repeatPwd())) {
      throw new WrongRepeatPasswordException();
    }
    var user = User.builder()
        .userInfo(req.userInfo())
        .build();
    return userReadService.toDto(userRepository.save(user));
  }
}
