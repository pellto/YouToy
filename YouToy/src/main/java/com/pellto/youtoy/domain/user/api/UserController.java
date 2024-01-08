package com.pellto.youtoy.domain.user.api;

import com.pellto.youtoy.domain.user.application.UserReadService;
import com.pellto.youtoy.domain.user.application.UserWriteService;
import com.pellto.youtoy.domain.user.dto.AuthorizedUser;
import com.pellto.youtoy.domain.user.dto.UserDto;
import com.pellto.youtoy.domain.user.dto.UserSignInRequest;
import com.pellto.youtoy.domain.user.dto.UserSignInResponse;
import com.pellto.youtoy.domain.user.dto.UserSignUpRequest;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

  private final UserWriteService userWriteService;
  private final UserReadService userReadService;

  @PostMapping
  public UserDto signUp(@RequestBody @Valid UserSignUpRequest req) {
    return userWriteService.signUp(req);
  }

  @PostMapping("/sign-in")
  public UserSignInResponse signIn(@RequestBody @Valid UserSignInRequest req) {
    return userWriteService.signIn(req);
  }

  @GetMapping
  public List<UserDto> findAll() {
    return userReadService.findAll();
  }

  @GetMapping("/info")
  public UserDto findUser(@AuthenticationPrincipal AuthorizedUser user) {
    return userReadService.findByUserUuid(user.getUserUuid());
  }
}
