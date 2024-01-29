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
  private final ContentsType contentsType;
  private final LocalDateTime createdAt;
  private final boolean isLike;

  @Builder
  public Interest(Long id, Long memberId, Long contentsId, String contentsType,
      LocalDateTime createdAt, boolean isLike) {
    this.id = id;
    this.memberId = Objects.requireNonNull(memberId);
    this.contentsId = Objects.requireNonNull(contentsId);
    this.contentsType = contentsTypeFromString(contentsType);
    this.createdAt = Temporal.createdAt(createdAt);
    this.isLike = isLike;
  }

  private ContentsType contentsTypeFromString(String contentsType) {
    switch (contentsType) {
      case ("POST") -> {
        return ContentsType.POST;
      }
      case ("COMMENT") -> {
        return ContentsType.COMMENT;
      }
      case ("REPLIED") -> {
        return ContentsType.REPLIED;
      }
      case ("VIDEO") -> {
        return ContentsType.VIDEO;
      }
      case ("SHORTS") -> {
        return ContentsType.SHORTS;
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
        .contentsType(contentsType.getType())
        .createdAt(createdAt)
        .isLike(isLike)
        .build();

  }
}
