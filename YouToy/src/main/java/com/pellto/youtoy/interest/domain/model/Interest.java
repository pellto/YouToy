package com.pellto.youtoy.interest.domain.model;

import com.pellto.youtoy.global.dto.interest.InterestDto;
import com.pellto.youtoy.global.util.Temporal;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Interest {

  private final Long id;
  private final Long memberId;
  private final Long contentsId;
  private final InterestContentsType interestContentsType;
  private final LocalDateTime createdAt;
  private final boolean isLike;

  @Builder
  public Interest(Long id, Long memberId, Long contentsId, String interestContentsType,
      LocalDateTime createdAt, boolean isLike) {
    this.id = id;
    this.memberId = Objects.requireNonNull(memberId);
    this.contentsId = Objects.requireNonNull(contentsId);
    this.interestContentsType = interestContentsTypeFromString(interestContentsType);
    this.createdAt = Temporal.createdAt(createdAt);
    this.isLike = isLike;
  }

  private InterestContentsType interestContentsTypeFromString(String interestContentsType) {
    switch (interestContentsType) {
      case ("POST") -> {
        return InterestContentsType.POST;
      }
      case ("COMMENT") -> {
        return InterestContentsType.COMMENT;
      }
      case ("REPLIED") -> {
        return InterestContentsType.REPLIED;
      }
      case ("VIDEO") -> {
        return InterestContentsType.VIDEO;
      }
      case ("SHORTS") -> {
        return InterestContentsType.SHORTS;
      }
      default -> {
        throw new IllegalArgumentException("invalid contents type");
      }
    }
  }

  public InterestDto toDto() {
    if (id == null) {
      throw new IllegalArgumentException("변경 불가");
    }
    return InterestDto.builder()
        .id(id)
        .memberId(memberId)
        .contentsId(contentsId)
        .interestContentsType(interestContentsType.getType())
        .createdAt(createdAt)
        .isLike(isLike)
        .build();

  }
}
