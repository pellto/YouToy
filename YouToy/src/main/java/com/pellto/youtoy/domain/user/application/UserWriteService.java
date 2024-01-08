package com.pellto.youtoy.domain.user.application;

import com.pellto.youtoy.domain.user.domain.User;
import com.pellto.youtoy.domain.user.dto.UserDto;
import com.pellto.youtoy.domain.user.dto.UserSignInRequest;
import com.pellto.youtoy.domain.user.dto.UserSignInResponse;
import com.pellto.youtoy.domain.user.dto.UserSignUpRequest;
import com.pellto.youtoy.domain.user.exception.NotExistUserException;
import com.pellto.youtoy.domain.user.exception.WrongPasswordException;
import com.pellto.youtoy.domain.user.exception.WrongRepeatPasswordException;
import com.pellto.youtoy.domain.user.repository.UserRepository;
import com.pellto.youtoy.global.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserWriteService {

  private final UserRepository userRepository;
  private final UserReadService userReadService;
  private final PasswordEncoder passwordEncoder;
  private final TokenProvider tokenProvider;

  public UserDto signUp(UserSignUpRequest req) {
    if (!req.userInfo().getPwd().equals(req.repeatPwd())) {
      throw new WrongRepeatPasswordException();
    }

    req.userInfo().encodePwd(passwordEncoder);
    var user = User.builder()
        .userInfo(req.userInfo())
        .build();
    // TODO: call channel domain create API
    return userReadService.toDto(userRepository.save(user));
  }

  @Transactional(readOnly = true)
  public UserSignInResponse signIn(UserSignInRequest req) {
    var user = userRepository.findByEmail(req.email())
        .orElseThrow(NotExistUserException::new);

    if (!passwordEncoder.matches(req.pwd(), user.getUserInfo().getPwd())) {
      throw new WrongPasswordException();
    }
    String token = tokenProvider.createToken(
        String.format("%s:%s:%s:%S:%s",
            user.getUserInfo().getEmail(),
            user.getUserInfo().getName(),
            user.getUuid().getValue(),
            user.getMemberType(),
            user.getPremiumLevel()
        )
    );
    return new UserSignInResponse(user.getUserInfo().getName(), user.getMemberType(), token);
  }
}
