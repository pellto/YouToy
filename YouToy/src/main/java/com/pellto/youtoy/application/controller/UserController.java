package com.pellto.youtoy.application.controller;

import com.pellto.youtoy.application.usecase.GetUserViewHistoryUsecase;
import com.pellto.youtoy.application.usecase.SignupUserUsecase;
import com.pellto.youtoy.domain.user.dto.LoginUserCommand;
import com.pellto.youtoy.domain.user.dto.RegisterUserCommand;
import com.pellto.youtoy.domain.user.dto.UpdateUserInfoCommand;
import com.pellto.youtoy.domain.user.dto.UserDto;
import com.pellto.youtoy.domain.user.service.UserReadService;
import com.pellto.youtoy.domain.user.service.UserWriteService;
import com.pellto.youtoy.domain.view.dto.ViewHistoryDto;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

  // TODO: login, logout to session controller
  private final UserReadService userReadService;
  private final UserWriteService userWriteService;
  private final SignupUserUsecase signupUserUsecase;
  private final GetUserViewHistoryUsecase getUserViewHistoryUsecase;

  @GetMapping("/{id}")
  public UserDto getUser(@PathVariable Long id) {
    return userReadService.getUser(id);
  }

  @GetMapping("/view-history/{id}")
  public List<ViewHistoryDto> getUserViewHistory(@PathVariable Long id) {
    return getUserViewHistoryUsecase.execute(id);
  }

  @PostMapping("/login")
  public UserDto login(@RequestBody LoginUserCommand cmd) {
    return userReadService.login(cmd);
  }

  @GetMapping("/{email}/logout")
  // TODO: log out to move sessionController using DELETE Method
  // TODO: check token or session when moving sessionController
  public void logout(@PathVariable String email) {
    userReadService.logout(email);
  }

  @PostMapping
  public UserDto signup(@RequestBody RegisterUserCommand cmd) {
    return signupUserUsecase.execute(cmd);
  }

  @PutMapping
  public void update(@Valid @RequestBody UpdateUserInfoCommand cmd) {
    userWriteService.update(cmd);
  }
}
