package com.pellto.youtoy.interest.domain.service;

import com.pellto.youtoy.global.dto.interest.InterestDto;
import com.pellto.youtoy.global.dto.interest.request.DisLikeRequest;
import com.pellto.youtoy.global.dto.interest.request.LikeRequest;
import com.pellto.youtoy.interest.domain.model.Interest;
import com.pellto.youtoy.interest.domain.port.in.InterestActionUsecase;
import com.pellto.youtoy.interest.domain.port.out.InterestEventPort;
import com.pellto.youtoy.interest.domain.port.out.LoadInterestPort;
import com.pellto.youtoy.interest.domain.port.out.SaveInterestPort;
import com.pellto.youtoy.interest.domain.port.out.http.InterestContentsExistHandlePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InterestActionWriteService implements InterestActionUsecase {

  private final SaveInterestPort saveInterestPort;
  private final LoadInterestPort loadInterestPort;
  private final InterestEventPort interestEventPort;
  private final InterestContentsExistHandlePort interestContentsExistHandlePort;

  @Override
  public InterestDto like(LikeRequest request) {
    if (!isExistContents(request.interestContentsType(), request.contentsId())) {
      throw new IllegalArgumentException("해당 컨텐츠가 없습니다.");
    }

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
    if (!isExistContents(request.interestContentsType(), request.contentsId())) {
      throw new IllegalArgumentException("해당 컨텐츠가 없습니다.");
    }

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

  private boolean isExistContents(String interestContentsType, Long contentsId) {
    return interestContentsExistHandlePort.isExistContents(interestContentsType, contentsId);
  }
}
