package com.pellto.youtoy.hello;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class Hello {
    @GetMapping("/hello")
    public String helloWorld() {
        return "hello";
    }
}
