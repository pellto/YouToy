package com.pellto.youtoy.domain.channel.api;

import com.pellto.youtoy.domain.channel.application.SubscribeService;
import com.pellto.youtoy.domain.channel.dto.CreateSubscribeRelRequest;
import com.pellto.youtoy.domain.channel.dto.SubscribeDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/subscribes")
@RestController
public class SubscribeController {

  private final SubscribeService subscribeService;

  @GetMapping
  public List<SubscribeDto> findAll() {
    return subscribeService.findAll();
  }

  @PostMapping
  public SubscribeDto subscribe(@RequestBody CreateSubscribeRelRequest req) {
    return subscribeService.subscribe(req);
  }

  @DeleteMapping("/{id}")
  public void unsubscribe(@PathVariable Long id) {
    subscribeService.unsubscribe(id);
  }

}
