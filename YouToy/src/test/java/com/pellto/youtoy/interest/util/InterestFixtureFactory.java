package com.pellto.youtoy.interest.util;

import com.pellto.youtoy.global.dto.interest.request.DisLikeRequest;
import com.pellto.youtoy.global.dto.interest.request.LikeRequest;
import com.pellto.youtoy.interest.domain.model.ContentsType;
import com.pellto.youtoy.interest.domain.model.Interest;
import java.time.LocalDateTime;

public class InterestFixtureFactory {

  private static final Long ID = 1L;
  private static final Long MEMBER_ID = 1L;
  private static final Long CONTENTS_ID = 1L;
  private static final ContentsType CONTENTS_TYPE = ContentsType.POST;
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();
  private static final boolean IS_LIKE = true;

  public static Interest create(Interest beforeSaved) {
    return Interest.builder()
        .id(ID)
        .memberId(beforeSaved.getMemberId())
        .contentsId(beforeSaved.getContentsId())
        .contentsType(beforeSaved.getContentsType().getType())
        .isLike(beforeSaved.isLike())
        .createdAt(beforeSaved.getCreatedAt())
        .build();
  }

  public static Interest create(boolean isLike) {
    return Interest.builder()
        .id(ID)
        .memberId(MEMBER_ID)
        .contentsId(CONTENTS_ID)
        .contentsType(CONTENTS_TYPE.getType())
        .isLike(isLike)
        .createdAt(CREATED_AT)
        .build();
  }

  public static Interest createBeforeSaved() {
    return Interest.builder()
        .memberId(MEMBER_ID)
        .contentsId(CONTENTS_ID)
        .contentsType(CONTENTS_TYPE.getType())
        .isLike(IS_LIKE)
        .build();
  }

  public static Interest create() {
    return Interest.builder()
        .id(ID)
        .memberId(MEMBER_ID)
        .contentsId(CONTENTS_ID)
        .contentsType(CONTENTS_TYPE.getType())
        .isLike(IS_LIKE)
        .createdAt(CREATED_AT)
        .build();
  }

  public static Interest createBeforeSaved(LikeRequest request) {
    return Interest.builder()
        .memberId(request.memberId())
        .contentsId(request.contentsId())
        .contentsType(request.contentsType())
        .isLike(IS_LIKE)
        .build();
  }

  public static Interest createBeforeSaved(DisLikeRequest request) {
    return Interest.builder()
        .memberId(request.memberId())
        .contentsId(request.contentsId())
        .contentsType(request.contentsType())
        .isLike(false)
        .build();
  }

  public static LikeRequest createLikeRequest() {
    return new LikeRequest(MEMBER_ID, CONTENTS_ID, CONTENTS_TYPE.getType());
  }

  public static DisLikeRequest createDislikeRequest() {
    return new DisLikeRequest(MEMBER_ID, CONTENTS_ID, CONTENTS_TYPE.getType());
  }
}
