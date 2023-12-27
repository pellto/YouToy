package com.pellto.youtoy.domain.community.util;

import com.pellto.youtoy.domain.community.domain.CommunityReplyCommentInterest;
import com.pellto.youtoy.domain.community.domain.PostReplyComment;
import com.pellto.youtoy.domain.community.dto.InterestRequest;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import java.time.LocalDateTime;

public class CommunityReplyCommentInterestFactory {

  private static final Long ID = 1L;
  private static final PostReplyComment INTERESTED_REPLY = PostReplyCommentFactory.createReplyComment();
  private static final String INTERESTING_USER_UUID_VALUE = "test-uuid";
  private static final UserUUID INTERESTING_USER_UUID = new UserUUID(INTERESTING_USER_UUID_VALUE);
  private static final boolean DISLIKE = false;
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();

  public static CommunityReplyCommentInterest createBeforeSavedCommentInterest() {
    return CommunityReplyCommentInterest.builder()
        .interestedReplyComment(INTERESTED_REPLY)
        .interestingUserUuid(INTERESTING_USER_UUID)
        .dislike(DISLIKE)
        .build();
  }

  public static CommunityReplyCommentInterest createInterest(PostReplyComment reply) {
    return CommunityReplyCommentInterest.builder()
        .id(ID)
        .interestedReplyComment(reply)
        .interestingUserUuid(INTERESTING_USER_UUID)
        .dislike(DISLIKE)
        .createdAt(CREATED_AT)
        .build();
  }

  public static CommunityReplyCommentInterest createInterest(PostReplyComment reply,
      UserUUID userUUID) {
    return CommunityReplyCommentInterest.builder()
        .id(ID)
        .interestedReplyComment(reply)
        .interestingUserUuid(userUUID)
        .dislike(DISLIKE)
        .createdAt(CREATED_AT)
        .build();
  }

  public static InterestRequest createInterestRequest() {
    return new InterestRequest(INTERESTING_USER_UUID_VALUE, DISLIKE);
  }
}
