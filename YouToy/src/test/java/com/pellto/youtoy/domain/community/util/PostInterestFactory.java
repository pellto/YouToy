package com.pellto.youtoy.domain.community.util;

import com.pellto.youtoy.domain.community.domain.CommunityPost;
import com.pellto.youtoy.domain.community.domain.PostInterest;
import com.pellto.youtoy.domain.community.dto.InterestRequest;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import java.time.LocalDateTime;

public class PostInterestFactory {

  private static final Long ID = 1L;
  private static final CommunityPost INTERESTED_POST = CommunityPostFactory.createPost();
  private static final String INTERESTING_USER_UUID_VALUE = "test-uuid";
  private static final UserUUID INTERESTING_USER_UUID = new UserUUID(INTERESTING_USER_UUID_VALUE);
  private static final boolean DISLIKE = false;
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();

  public static PostInterest createBeforeSavedPostInterest() {
    return PostInterest.builder()
        .interestedPost(INTERESTED_POST)
        .interestingUserUuid(INTERESTING_USER_UUID)
        .dislike(DISLIKE)
        .build();
  }

  public static PostInterest createInterest(CommunityPost post) {
    return PostInterest.builder()
        .id(ID)
        .interestedPost(post)
        .interestingUserUuid(INTERESTING_USER_UUID)
        .dislike(DISLIKE)
        .createdAt(CREATED_AT)
        .build();
  }

  public static PostInterest createInterest(CommunityPost post, UserUUID userUUID) {
    return PostInterest.builder()
        .id(ID)
        .interestedPost(post)
        .interestingUserUuid(userUUID)
        .dislike(DISLIKE)
        .createdAt(CREATED_AT)
        .build();
  }

  public static InterestRequest createInterestPostRequest() {
    return new InterestRequest(INTERESTING_USER_UUID_VALUE, DISLIKE);
  }
}
