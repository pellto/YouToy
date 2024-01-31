package com.pellto.youtoy.interest.domain.service;

import com.pellto.youtoy.global.dto.interest.InterestDto;
import com.pellto.youtoy.global.dto.interest.request.DisLikeRequest;
import com.pellto.youtoy.global.dto.interest.request.LikeRequest;
import com.pellto.youtoy.interest.domain.model.Interest;
import com.pellto.youtoy.interest.domain.port.in.InterestActionUsecase;
import com.pellto.youtoy.interest.domain.port.out.InterestEventPort;
import com.pellto.youtoy.interest.domain.port.out.LoadInterestPort;
import com.pellto.youtoy.interest.domain.port.out.SaveInterestPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InterestActionWriteService implements InterestActionUsecase {

  private final SaveInterestPort saveInterestPort;
  private final LoadInterestPort loadInterestPort;
  private final InterestEventPort interestEventPort;

  @Override
  public InterestDto like(LikeRequest request) {
    var interest = Interest.builder()
        .memberId(request.memberId())
        .contentsId(request.contentsId())
        .interestContentsType(request.interestContentsType())
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
        .interestContentsType(request.interestContentsType())
        .isLike(false)
        .build();

    var dto = saveInterestPort.save(interest).toDto();
    interestEventPort.dislikeEvent(dto);
    return dto;
  }

  @Override
  public void deleteInterest(Long interestId) {
    var interest = loadInterestPort.load(interestId);
    var dto = interest.toDto();
    saveInterestPort.delete(interest);
    if (dto.isLike()) {
      interestEventPort.deletedLikeEvent(dto);
    } else {
      interestEventPort.deletedDislikeEvent(dto);
    }
  }
}
