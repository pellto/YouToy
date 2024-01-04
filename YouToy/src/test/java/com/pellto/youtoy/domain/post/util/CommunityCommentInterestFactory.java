package com.pellto.youtoy.domain.post.util;

import com.pellto.youtoy.domain.base.dto.WriteInterestRequest;
import com.pellto.youtoy.domain.post.domain.PostComment;
import com.pellto.youtoy.domain.post.domain.PostCommentInterest;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import java.time.LocalDateTime;

public class CommunityCommentInterestFactory {

  private static final Long ID = 1L;
  private static final PostComment INTERESTED_COMMENT = CommunityCommentFactory.createCommunityComment();
  private static final String INTERESTING_USER_UUID_VALUE = "test-uuid";
  private static final UserUUID INTERESTING_USER_UUID = new UserUUID(INTERESTING_USER_UUID_VALUE);
  private static final boolean DISLIKE = false;
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();

  public static PostCommentInterest createBeforeSavedCommentInterest() {
    return PostCommentInterest.builder()
        .interestedComment(INTERESTED_COMMENT)
        .interestingUserUuid(INTERESTING_USER_UUID)
        .dislike(DISLIKE)
        .build();
  }

  public static PostCommentInterest createInterest(PostComment comment) {
    return PostCommentInterest.builder()
        .id(ID)
        .interestedComment(comment)
        .interestingUserUuid(INTERESTING_USER_UUID)
        .dislike(DISLIKE)
        .createdAt(CREATED_AT)
        .build();
  }

  public static PostCommentInterest createInterest(PostComment comment,
      UserUUID userUUID) {
    return PostCommentInterest.builder()
        .id(ID)
        .interestedComment(comment)
        .interestingUserUuid(userUUID)
        .dislike(DISLIKE)
        .createdAt(CREATED_AT)
        .build();
  }

  public static WriteInterestRequest createInterestRequest() {
    return new WriteInterestRequest(INTERESTED_COMMENT.getId(), INTERESTING_USER_UUID_VALUE,
        DISLIKE);
  }
}
