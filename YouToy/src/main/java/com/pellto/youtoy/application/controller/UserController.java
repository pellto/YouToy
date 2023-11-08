package com.pellto.youtoy.application.controller;

import com.pellto.youtoy.domain.user.dto.LoginUserCommand;
import com.pellto.youtoy.domain.user.dto.RegisterUserCommand;
import com.pellto.youtoy.domain.user.dto.UserDto;
import com.pellto.youtoy.domain.user.service.UserReadService;
import com.pellto.youtoy.domain.user.service.UserWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    // TODO: login, logout to session controller
    private final UserWriteService userWriteService;
    private final UserReadService userReadService;

    @PostMapping
    public UserDto signIn(@RequestBody RegisterUserCommand cmd) {
        return userReadService.toDto(userWriteService.signIn(cmd));
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
}
