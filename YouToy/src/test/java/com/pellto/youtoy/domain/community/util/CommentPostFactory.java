package com.pellto.youtoy.domain.community.util;

import com.pellto.youtoy.domain.community.domain.CommunityComment;
import com.pellto.youtoy.domain.community.domain.CommunityPost;
import com.pellto.youtoy.domain.community.dto.CommunityPostDto;
import com.pellto.youtoy.domain.community.dto.WritePostRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommentPostFactory {

  private static final Long ID = 1L;
  private static final Long CHANNEL_ID = 1L;
  private static final String CONTENT = "content";
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();
  private static final boolean MODIFIED = false;
  private static final LocalDateTime MODIFIED_AT = CREATED_AT;
  private static final List<CommunityComment> COMMENTS = new ArrayList<>();

  public static CommunityPost createBeforeSavedPost() {
    return CommunityPost.builder()
        .channelId(CHANNEL_ID)
        .content(CONTENT)
        .build();
  }

  public static CommunityPost createPost() {
    return CommunityPost.builder()
        .id(ID)
        .channelId(CHANNEL_ID)
        .content(CONTENT)
        .createdAt(CREATED_AT)
        .modifiedAt(MODIFIED_AT)
        .build();
  }

  public static CommunityPostDto createPostDto() {
    return new CommunityPostDto(ID, CHANNEL_ID, CONTENT, MODIFIED, COMMENTS.size(), CREATED_AT);
  }

  public static WritePostRequest createWritePostRequest() {
    return new WritePostRequest(CHANNEL_ID, CONTENT);
  }
}
