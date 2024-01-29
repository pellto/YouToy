package com.pellto.youtoy.interest.domain.service;

import com.pellto.youtoy.global.dto.interest.InterestDto;
import com.pellto.youtoy.global.dto.interest.request.DisLikeRequest;
import com.pellto.youtoy.global.dto.interest.request.LikeRequest;
import com.pellto.youtoy.interest.domain.model.Interest;
import com.pellto.youtoy.interest.domain.port.in.InterestActionUsecase;
import com.pellto.youtoy.interest.domain.port.out.InterestEventPort;
import com.pellto.youtoy.interest.domain.port.out.SaveInterestPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InterestActionWriteService implements InterestActionUsecase {

  private final SaveInterestPort saveInterestPort;
  private final InterestEventPort interestEventPort;

  @Override
  public InterestDto like(LikeRequest request) {
    var interest = Interest.builder()
        .memberId(request.memberId())
        .contentsId(request.contentsId())
        .contentsType(request.contentsType())
        .isLike(true)
        .build();

    var dto = saveInterestPort.save(interest).toDto();
    interestEventPort.likeEvent(dto);
    return dto;
  }

  @Override
  public InterestDto disLike(DisLikeRequest request) {
    var interest = Interest.builder()
        .memberId(request.memberId())
        .contentsId(request.contentsId())
        .contentsType(request.contentsType())
        .isLike(false)
        .build();

    var dto = saveInterestPort.save(interest).toDto();
    interestEventPort.dislikeEvent(dto);
    return dto;
  }
}
