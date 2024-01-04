package com.pellto.youtoy.domain.video.util;

import com.pellto.youtoy.domain.base.dto.WriteInterestRequest;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import com.pellto.youtoy.domain.video.domain.VideoReplyComment;
import com.pellto.youtoy.domain.video.domain.VideoReplyCommentInterest;
import com.pellto.youtoy.domain.video.dto.VideoInterestDto;
import java.time.LocalDateTime;

public class VideoReplyInterestFactory {

  private static final VideoReplyComment REPLY = VideoReplyFactory.create();
  private static final UserUUID INTERESTING_USER_UUID = new UserUUID("test-user");
  private static final Long ID = 1L;
  private static final boolean DISLIKE = false;
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();

  public static VideoReplyCommentInterest create(VideoReplyComment reply, UserUUID userUuid) {
    return VideoReplyCommentInterest.builder()
        .interestedReplyComment(reply)
        .interestingUserUuid(userUuid)
        .id(ID)
        .dislike(DISLIKE)
        .createdAt(CREATED_AT)
        .build();
  }

  public static VideoReplyCommentInterest create(VideoReplyComment reply) {
    return VideoReplyCommentInterest.builder()
        .interestedReplyComment(reply)
        .interestingUserUuid(INTERESTING_USER_UUID)
        .id(ID)
        .dislike(DISLIKE)
        .createdAt(CREATED_AT)
        .build();
  }

  public static VideoReplyCommentInterest createBeforeSaved(WriteInterestRequest req) {
    return VideoReplyCommentInterest.builder()
        .interestedReplyComment(VideoReplyFactory.create(req.contentsId()))
        .interestingUserUuid(new UserUUID(req.interestingUserUuid()))
        .dislike(req.dislike())
        .build();
  }

  public static VideoReplyCommentInterest createBeforeSaved() {
    return VideoReplyCommentInterest.builder()
        .interestedReplyComment(REPLY)
        .interestingUserUuid(INTERESTING_USER_UUID)
        .dislike(DISLIKE)
        .build();
  }

  public static VideoReplyCommentInterest create() {
    return VideoReplyCommentInterest.builder()
        .interestedReplyComment(REPLY)
        .interestingUserUuid(INTERESTING_USER_UUID)
        .id(ID)
        .dislike(DISLIKE)
        .createdAt(CREATED_AT)
        .build();
  }

  public static VideoReplyCommentInterest createBeforeSaved(VideoReplyComment reply) {
    return VideoReplyCommentInterest.builder()
        .interestedReplyComment(reply)
        .interestingUserUuid(INTERESTING_USER_UUID)
        .dislike(DISLIKE)
        .build();
  }

  public static VideoInterestDto createDto() {
    return new VideoInterestDto(ID, REPLY.getId(), INTERESTING_USER_UUID.getValue(), DISLIKE);
  }

  public static VideoInterestDto createDto(VideoReplyCommentInterest interest) {
    return new VideoInterestDto(interest.getId(), interest.getInterestedReplyComment().getId(),
        interest.getInterestingUserUuid().getValue(),
        interest.isDislike());
  }

  public static WriteInterestRequest createWriteInterestRequest() {
    return new WriteInterestRequest(REPLY.getId(), INTERESTING_USER_UUID.getValue(), DISLIKE);
  }
}
