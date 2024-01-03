package com.pellto.youtoy.domain.video.util;

import com.pellto.youtoy.domain.base.dto.WriteInterestRequest;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import com.pellto.youtoy.domain.video.domain.Video;
import com.pellto.youtoy.domain.video.domain.VideoInterest;
import com.pellto.youtoy.domain.video.dto.VideoInterestDto;
import java.time.LocalDateTime;

public class VideoInterestFactory {

  private static final Video CONTENTS = VideoFactory.create();
  private static final UserUUID INTERESTING_USER_UUID = new UserUUID("test-user");
  private static final Long ID = 1L;
  private static final boolean DISLIKE = false;
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();

  public static VideoInterest create(Video contents, UserUUID userUuid) {
    return VideoInterest.builder()
        .interestedContents(contents)
        .interestingUserUuid(userUuid)
        .id(ID)
        .dislike(DISLIKE)
        .createdAt(CREATED_AT)
        .build();
  }

  public static VideoInterest create(Video contents) {
    return VideoInterest.builder()
        .interestedContents(contents)
        .interestingUserUuid(INTERESTING_USER_UUID)
        .id(ID)
        .dislike(DISLIKE)
        .createdAt(CREATED_AT)
        .build();
  }

  public static VideoInterest createBeforeSaved(WriteInterestRequest req) {
    return VideoInterest.builder()
        .interestedContents(VideoFactory.create(req.contentsId()))
        .interestingUserUuid(new UserUUID(req.interestingUserUuid()))
        .dislike(req.dislike())
        .build();
  }

  public static VideoInterest createBeforeSaved() {
    return VideoInterest.builder()
        .interestedContents(CONTENTS)
        .interestingUserUuid(INTERESTING_USER_UUID)
        .dislike(DISLIKE)
        .build();
  }

  public static VideoInterest create() {
    return VideoInterest.builder()
        .interestedContents(CONTENTS)
        .interestingUserUuid(INTERESTING_USER_UUID)
        .id(ID)
        .dislike(DISLIKE)
        .createdAt(CREATED_AT)
        .build();
  }

  public static VideoInterest createBeforeSaved(Video contents) {
    return VideoInterest.builder()
        .interestedContents(contents)
        .interestingUserUuid(INTERESTING_USER_UUID)
        .dislike(DISLIKE)
        .build();
  }

  public static VideoInterestDto createDto() {
    return new VideoInterestDto(ID, CONTENTS.getId(), INTERESTING_USER_UUID.getValue(), DISLIKE);
  }

  public static VideoInterestDto createDto(VideoInterest interest) {
    return new VideoInterestDto(interest.getId(), interest.getInterestedContents().getId(),
        interest.getInterestingUserUuid().getValue(),
        interest.isDislike());
  }

  public static WriteInterestRequest createWriteInterestRequest() {
    return new WriteInterestRequest(CONTENTS.getId(), INTERESTING_USER_UUID.getValue(), DISLIKE);
  }
}
