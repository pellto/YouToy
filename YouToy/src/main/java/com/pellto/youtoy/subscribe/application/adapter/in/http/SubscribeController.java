package com.pellto.youtoy.subscribe.application.adapter.in.http;

import com.pellto.youtoy.global.dto.subscribe.SubscribeDto;
import com.pellto.youtoy.global.dto.subscribe.request.ChangeSubscribeLevelRequest;
import com.pellto.youtoy.global.dto.subscribe.request.SubscribeRequest;
import com.pellto.youtoy.global.dto.subscribe.request.UnsubscribeRequest;
import com.pellto.youtoy.subscribe.domain.port.in.ChangeSubscribeLevelUsecase;
import com.pellto.youtoy.subscribe.domain.port.in.SubscribeUsecase;
import com.pellto.youtoy.subscribe.domain.port.in.UnsubscribeUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscribes")
@RequiredArgsConstructor
public class SubscribeController {

  private final SubscribeUsecase subscribeUsecase;
  private final UnsubscribeUsecase unsubscribeUsecase;
  private final ChangeSubscribeLevelUsecase changeSubscribeLevelUsecase;

  @PostMapping
  public SubscribeDto subscribe(@RequestBody SubscribeRequest request) {
    System.out.println("request = " + request);
    return subscribeUsecase.subscribe(request);
  }

  @DeleteMapping
  public void unsubscribe(@RequestBody UnsubscribeRequest request) {
    unsubscribeUsecase.unsubscribe(request);
  }

  @PatchMapping
  public void changeLevel(@RequestBody ChangeSubscribeLevelRequest request) {
    changeSubscribeLevelUsecase.changeLevel(request);
  }
}
