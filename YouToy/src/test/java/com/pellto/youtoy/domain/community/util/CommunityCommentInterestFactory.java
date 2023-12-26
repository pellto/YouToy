package com.pellto.youtoy.domain.community.util;

import com.pellto.youtoy.domain.community.domain.CommunityComment;
import com.pellto.youtoy.domain.community.domain.CommunityCommentInterest;
import com.pellto.youtoy.domain.community.dto.InterestRequest;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import java.time.LocalDateTime;

public class CommunityCommentInterestFactory {

  private static final Long ID = 1L;
  private static final CommunityComment INTERESTED_COMMENT = CommunityCommentFactory.createCommunityComment();
  private static final String INTERESTING_USER_UUID_VALUE = "test-uuid";
  private static final UserUUID INTERESTING_USER_UUID = new UserUUID(INTERESTING_USER_UUID_VALUE);
  private static final boolean DISLIKE = false;
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();

  public static CommunityCommentInterest createBeforeSavedCommentInterest() {
    return CommunityCommentInterest.builder()
        .interestedCommunityComment(INTERESTED_COMMENT)
        .interestingUserUuid(INTERESTING_USER_UUID)
        .dislike(DISLIKE)
        .build();
  }

  public static CommunityCommentInterest createInterest(CommunityComment comment) {
    return CommunityCommentInterest.builder()
        .id(ID)
        .interestedCommunityComment(comment)
        .interestingUserUuid(INTERESTING_USER_UUID)
        .dislike(DISLIKE)
        .createdAt(CREATED_AT)
        .build();
  }

  public static CommunityCommentInterest createInterest(CommunityComment comment,
      UserUUID userUUID) {
    return CommunityCommentInterest.builder()
        .id(ID)
        .interestedCommunityComment(comment)
        .interestingUserUuid(userUUID)
        .dislike(DISLIKE)
        .createdAt(CREATED_AT)
        .build();
  }

  public static InterestRequest createInterestRequest() {
    return new InterestRequest(INTERESTING_USER_UUID_VALUE, DISLIKE);
  }
}
