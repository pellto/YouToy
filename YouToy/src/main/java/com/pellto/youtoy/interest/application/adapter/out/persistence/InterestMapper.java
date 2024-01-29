package com.pellto.youtoy.interest.application.adapter.out.persistence;

import com.pellto.youtoy.interest.domain.model.Interest;
import org.springframework.stereotype.Component;

@Component
public class InterestMapper {

  public Interest toDomain(InterestEntity entity) {
    return Interest.builder()
        .id(entity.getId())
        .memberId(entity.getMemberId())
        .contentsId(entity.getContentsId())
        .contentsType(entity.getContentsType())
        .createdAt(entity.getCreatedAt())
        .isLike(entity.isLike())
        .build();
  }

  public InterestEntity toEntity(Interest interest) {
    return InterestEntity.builder()
        .id(interest.getId())
        .memberId(interest.getMemberId())
        .contentsId(interest.getContentsId())
        .contentsType(interest.getContentsType().getType())
        .createdAt(interest.getCreatedAt())
        .isLike(interest.isLike())
        .build();
  }
}
