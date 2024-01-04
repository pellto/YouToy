package com.pellto.youtoy.domain.post.util;

import com.pellto.youtoy.domain.base.dto.WriteInterestRequest;
import com.pellto.youtoy.domain.post.domain.PostReply;
import com.pellto.youtoy.domain.post.domain.PostReplyInterest;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import java.time.LocalDateTime;

public class CommunityReplyCommentInterestFactory {

  private static final Long ID = 1L;
  private static final PostReply INTERESTED_REPLY = PostReplyCommentFactory.createReplyComment();
  private static final String INTERESTING_USER_UUID_VALUE = "test-uuid";
  private static final UserUUID INTERESTING_USER_UUID = new UserUUID(INTERESTING_USER_UUID_VALUE);
  private static final boolean DISLIKE = false;
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();

  public static PostReplyInterest createBeforeSavedCommentInterest() {
    return PostReplyInterest.builder()
        .interestedReply(INTERESTED_REPLY)
        .interestingUserUuid(INTERESTING_USER_UUID)
        .dislike(DISLIKE)
        .build();
  }

  public static PostReplyInterest createInterest(PostReply reply) {
    return PostReplyInterest.builder()
        .id(ID)
        .interestedReply(reply)
        .interestingUserUuid(INTERESTING_USER_UUID)
        .dislike(DISLIKE)
        .createdAt(CREATED_AT)
        .build();
  }

  public static PostReplyInterest createInterest(PostReply reply,
      UserUUID userUUID) {
    return PostReplyInterest.builder()
        .id(ID)
        .interestedReply(reply)
        .interestingUserUuid(userUUID)
        .dislike(DISLIKE)
        .createdAt(CREATED_AT)
        .build();
  }

  public static WriteInterestRequest createInterestRequest() {
    return new WriteInterestRequest(INTERESTED_REPLY.getId(), INTERESTING_USER_UUID_VALUE, DISLIKE);
  }
}
