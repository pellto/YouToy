package com.pellto.youtoy.domain.user.api;

import com.pellto.youtoy.domain.user.application.UserReadService;
import com.pellto.youtoy.domain.user.application.UserWriteService;
import com.pellto.youtoy.domain.user.dto.UserDto;
import com.pellto.youtoy.domain.user.dto.UserSignUpRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  public UserDto signUp(@RequestBody UserSignUpRequest req) {
    return userWriteService.signUp(req);
  }

  @GetMapping
  public List<UserDto> findAll() {
    return userReadService.findAll();
  }

  @GetMapping("/{id}")
  public UserDto findById(@PathVariable Long id) {
    return userReadService.findById(id);
  }

}
