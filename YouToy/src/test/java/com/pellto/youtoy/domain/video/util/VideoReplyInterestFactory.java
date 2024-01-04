package com.pellto.youtoy.domain.video.util;

import com.pellto.youtoy.domain.base.dto.WriteInterestRequest;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import com.pellto.youtoy.domain.video.domain.VideoReply;
import com.pellto.youtoy.domain.video.domain.VideoReplyInterest;
import com.pellto.youtoy.domain.video.dto.VideoInterestDto;
import java.time.LocalDateTime;

public class VideoReplyInterestFactory {

  private static final VideoReply REPLY = VideoReplyFactory.create();
  private static final UserUUID INTERESTING_USER_UUID = new UserUUID("test-user");
  private static final Long ID = 1L;
  private static final boolean DISLIKE = false;
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();

  public static VideoReplyInterest create(VideoReply reply, UserUUID userUuid) {
    return VideoReplyInterest.builder()
        .interestedReply(reply)
        .interestingUserUuid(userUuid)
        .id(ID)
        .dislike(DISLIKE)
        .createdAt(CREATED_AT)
        .build();
  }

  public static VideoReplyInterest create(VideoReply reply) {
    return VideoReplyInterest.builder()
        .interestedReply(reply)
        .interestingUserUuid(INTERESTING_USER_UUID)
        .id(ID)
        .dislike(DISLIKE)
        .createdAt(CREATED_AT)
        .build();
  }

  public static VideoReplyInterest createBeforeSaved(WriteInterestRequest req) {
    return VideoReplyInterest.builder()
        .interestedReply(VideoReplyFactory.create(req.contentsId()))
        .interestingUserUuid(new UserUUID(req.interestingUserUuid()))
        .dislike(req.dislike())
        .build();
  }

  public static VideoReplyInterest createBeforeSaved() {
    return VideoReplyInterest.builder()
        .interestedReply(REPLY)
        .interestingUserUuid(INTERESTING_USER_UUID)
        .dislike(DISLIKE)
        .build();
  }

  public static VideoReplyInterest create() {
    return VideoReplyInterest.builder()
        .interestedReply(REPLY)
        .interestingUserUuid(INTERESTING_USER_UUID)
        .id(ID)
        .dislike(DISLIKE)
        .createdAt(CREATED_AT)
        .build();
  }

  public static VideoReplyInterest createBeforeSaved(VideoReply reply) {
    return VideoReplyInterest.builder()
        .interestedReply(reply)
        .interestingUserUuid(INTERESTING_USER_UUID)
        .dislike(DISLIKE)
        .build();
  }

  public static VideoInterestDto createDto() {
    return new VideoInterestDto(ID, REPLY.getId(), INTERESTING_USER_UUID.getValue(), DISLIKE);
  }

  public static VideoInterestDto createDto(VideoReplyInterest interest) {
    return new VideoInterestDto(interest.getId(), interest.getInterestedReply().getId(),
        interest.getInterestingUserUuid().getValue(),
        interest.isDislike());
  }

  public static WriteInterestRequest createWriteInterestRequest() {
    return new WriteInterestRequest(REPLY.getId(), INTERESTING_USER_UUID.getValue(), DISLIKE);
  }
}
