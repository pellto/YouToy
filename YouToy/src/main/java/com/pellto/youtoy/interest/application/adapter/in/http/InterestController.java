package com.pellto.youtoy.interest.application.adapter.in.http;

import com.pellto.youtoy.global.dto.interest.InterestDto;
import com.pellto.youtoy.global.dto.interest.request.DisLikeRequest;
import com.pellto.youtoy.global.dto.interest.request.LikeRequest;
import com.pellto.youtoy.interest.domain.port.in.InterestActionUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interests")
@RequiredArgsConstructor
public class InterestController {

  private final InterestActionUsecase interestActionUsecase;

  @PostMapping("/like")
  public InterestDto like(@RequestBody LikeRequest request) {
    return interestActionUsecase.like(request);
  }

  @PostMapping("/dislike")
  public InterestDto dislike(@RequestBody DisLikeRequest request) {
    return interestActionUsecase.disLike(request);
  }
}
