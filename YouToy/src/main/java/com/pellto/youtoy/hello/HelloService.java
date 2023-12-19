package com.pellto.youtoy.hello;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HelloService {

  private final HelloRepository helloRepository;

  public List<Hello> findAll() {
    return helloRepository.findAll();
  }

  public Optional<Hello> findById(Long id) {
    return helloRepository.findById(id);
  }

  public Hello save(String content) {
    var hello = Hello.builder().content(content).build();
    return helloRepository.save(hello);
  }
}
