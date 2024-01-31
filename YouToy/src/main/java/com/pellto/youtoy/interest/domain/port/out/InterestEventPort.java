package com.pellto.youtoy.interest.domain.port.out;

import com.pellto.youtoy.global.dto.interest.InterestDto;

public interface InterestEventPort {

  void deletedDislikeEvent(InterestDto dto);

  void deletedLikeEvent(InterestDto dto);

  void likeEvent(InterestDto dto);

  void dislikeEvent(InterestDto dto);
}
