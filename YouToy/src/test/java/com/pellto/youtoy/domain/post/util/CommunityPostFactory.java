package com.pellto.youtoy.domain.post.util;

import com.pellto.youtoy.domain.post.domain.Post;
import com.pellto.youtoy.domain.post.domain.PostComment;
import com.pellto.youtoy.domain.post.dto.PostDto;
import com.pellto.youtoy.domain.post.dto.PostWriteRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommunityPostFactory {

  private static final Long ID = 1L;
  private static final Long CHANNEL_ID = 1L;
  private static final String CONTENT = "content";
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();
  private static final boolean MODIFIED = false;
  private static final LocalDateTime MODIFIED_AT = CREATED_AT;
  private static final int LIKE_COUNT = 0;
  private static final List<PostComment> COMMENTS = new ArrayList<>();

  public static Post createBeforeSavedPost() {
    return Post.builder()
        .channelId(CHANNEL_ID)
        .content(CONTENT)
        .build();
  }

  public static Post createPost() {
    return Post.builder()
        .id(ID)
        .channelId(CHANNEL_ID)
        .content(CONTENT)
        .createdAt(CREATED_AT)
        .modifiedAt(MODIFIED_AT)
        .build();
  }

  public static PostDto createPostDto() {
    return new PostDto(ID, CHANNEL_ID, CONTENT, MODIFIED, COMMENTS.size(),
        LIKE_COUNT, CREATED_AT);
  }

  public static PostWriteRequest createWritePostRequest() {
    return new PostWriteRequest(CHANNEL_ID, CONTENT);
  }
}
