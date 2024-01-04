package com.pellto.youtoy.domain.video.util;

import com.pellto.youtoy.domain.base.dto.WriteInterestRequest;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import com.pellto.youtoy.domain.video.domain.VideoComment;
import com.pellto.youtoy.domain.video.domain.VideoCommentInterest;
import com.pellto.youtoy.domain.video.dto.VideoInterestDto;
import java.time.LocalDateTime;

public class VideoCommentInterestFactory {

  private static final VideoComment COMMENT = VideoCommentFactory.create();
  private static final UserUUID INTERESTING_USER_UUID = new UserUUID("test-user");
  private static final Long ID = 1L;
  private static final boolean DISLIKE = false;
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();

  public static VideoCommentInterest create(VideoComment comment, UserUUID userUuid) {
    return VideoCommentInterest.builder()
        .interestedComment(comment)
        .interestingUserUuid(userUuid)
        .id(ID)
        .dislike(DISLIKE)
        .createdAt(CREATED_AT)
        .build();
  }

  public static VideoCommentInterest create(VideoComment comment) {
    return VideoCommentInterest.builder()
        .interestedComment(comment)
        .interestingUserUuid(INTERESTING_USER_UUID)
        .id(ID)
        .dislike(DISLIKE)
        .createdAt(CREATED_AT)
        .build();
  }

  public static VideoCommentInterest createBeforeSaved(WriteInterestRequest req) {
    return VideoCommentInterest.builder()
        .interestedComment(VideoCommentFactory.create(req.contentsId()))
        .interestingUserUuid(new UserUUID(req.interestingUserUuid()))
        .dislike(req.dislike())
        .build();
  }

  public static VideoCommentInterest createBeforeSaved() {
    return VideoCommentInterest.builder()
        .interestedComment(COMMENT)
        .interestingUserUuid(INTERESTING_USER_UUID)
        .dislike(DISLIKE)
        .build();
  }

  public static VideoCommentInterest create() {
    return VideoCommentInterest.builder()
        .interestedComment(COMMENT)
        .interestingUserUuid(INTERESTING_USER_UUID)
        .id(ID)
        .dislike(DISLIKE)
        .createdAt(CREATED_AT)
        .build();
  }

  public static VideoCommentInterest createBeforeSaved(VideoComment comment) {
    return VideoCommentInterest.builder()
        .interestedComment(comment)
        .interestingUserUuid(INTERESTING_USER_UUID)
        .dislike(DISLIKE)
        .build();
  }

  public static VideoInterestDto createDto() {
    return new VideoInterestDto(ID, COMMENT.getId(), INTERESTING_USER_UUID.getValue(), DISLIKE);
  }

  public static VideoInterestDto createDto(VideoCommentInterest interest) {
    return new VideoInterestDto(interest.getId(), interest.getInterestedComment().getId(),
        interest.getInterestingUserUuid().getValue(),
        interest.isDislike());
  }

  public static WriteInterestRequest createWriteInterestRequest() {
    return new WriteInterestRequest(COMMENT.getId(), INTERESTING_USER_UUID.getValue(), DISLIKE);
  }
}
