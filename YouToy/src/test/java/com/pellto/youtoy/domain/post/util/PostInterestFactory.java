package com.pellto.youtoy.domain.post.util;

import com.pellto.youtoy.domain.base.dto.WriteInterestRequest;
import com.pellto.youtoy.domain.post.domain.Post;
import com.pellto.youtoy.domain.post.domain.PostInterest;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import java.time.LocalDateTime;

public class PostInterestFactory {

  private static final Long ID = 1L;
  private static final Post INTERESTED_POST = CommunityPostFactory.createPost();
  private static final String INTERESTING_USER_UUID_VALUE = "test-uuid";
  private static final UserUUID INTERESTING_USER_UUID = new UserUUID(INTERESTING_USER_UUID_VALUE);
  private static final boolean DISLIKE = false;
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();

  public static PostInterest createBeforeSavedPostInterest() {
    return PostInterest.builder()
        .interestedContents(INTERESTED_POST)
        .interestingUserUuid(INTERESTING_USER_UUID)
        .dislike(DISLIKE)
        .build();
  }

  public static PostInterest createInterest(Post post) {
    return PostInterest.builder()
        .id(ID)
        .interestedContents(post)
        .interestingUserUuid(INTERESTING_USER_UUID)
        .dislike(DISLIKE)
        .createdAt(CREATED_AT)
        .build();
  }

  public static PostInterest createInterest(Post post, UserUUID userUUID) {
    return PostInterest.builder()
        .id(ID)
        .interestedContents(post)
        .interestingUserUuid(userUUID)
        .dislike(DISLIKE)
        .createdAt(CREATED_AT)
        .build();
  }

  public static WriteInterestRequest createInterestPostRequest() {
    return new WriteInterestRequest(INTERESTED_POST.getId(), INTERESTING_USER_UUID_VALUE, DISLIKE);
  }
}
