package com.pellto.youtoy.interest.domain.port.in;

import com.pellto.youtoy.global.dto.interest.InterestDto;
import com.pellto.youtoy.global.dto.interest.request.DisLikeRequest;
import com.pellto.youtoy.global.dto.interest.request.LikeRequest;

public interface InterestActionUsecase {

  InterestDto like(LikeRequest request);

  InterestDto disLike(DisLikeRequest request);

  void deleteInterest(Long interestId);

}
