package com.pellto.youtoy.application.controller;

import com.pellto.youtoy.domain.user.dto.RegisterUserCommand;
import com.pellto.youtoy.domain.user.entity.User;
import com.pellto.youtoy.domain.user.service.UserWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserWriteService userWriteService;

    @PostMapping
    public User signIn(@RequestBody RegisterUserCommand cmd) {
        return userWriteService.signIn(cmd);
    }
}
