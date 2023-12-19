package com.pellto.youtoy.hello;

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
@RequestMapping("/hello")
public class HelloController {

  private final HelloService helloService;

  @PostMapping
  public Hello save(@RequestBody String req) {
    return helloService.save(req);
  }

  @GetMapping
  public List<Hello> get() {
    return helloService.findAll();
  }

  @GetMapping("/{id}")
  public Hello getById(@PathVariable Long id) {
    return helloService.findById(id).orElseThrow();
  }
}
