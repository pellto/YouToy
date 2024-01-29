package com.pellto.youtoy.post.util;

import com.pellto.youtoy.global.dto.post.request.WritePostRequest;
import com.pellto.youtoy.post.domain.model.Post;
import com.pellto.youtoy.post.domain.model.PostContent;
import java.time.LocalDateTime;

public class PostFixtureFactory {

  private static final Long ID = 1L;
  private static final Long CHANNEL_ID = 1L;
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();
  private static final String TITLE = "test title";
  private static final String CONTENT = "test content";
  private static final PostContent POST_CONTENT = new PostContent(TITLE, CONTENT);
  private static final Long VIEW_COUNT = 0L;
  private static final Long LIKE_COUNT = 0L;
  private static final LocalDateTime UPDATED_AT = CREATED_AT;

  public static Post create(Post beforeSaved) {
    return create(beforeSaved.getChannelId(), beforeSaved.getPostContent());
  }

  public static Post createBeforeSaved(String title, String content) {
    var postContent = new PostContent(title, content);
    return createBeforeSaved(postContent);
  }

  public static Post createBeforeSaved(WritePostRequest request) {
    var postContent = new PostContent(request.title(), request.content());
    return createBeforeSaved(postContent);
  }

  private static Post createBeforeSaved(PostContent postContent) {
    return Post.builder().channelId(CHANNEL_ID).postContent(postContent).build();
  }

  public static Post createBeforeSaved() {
    return createBeforeSaved(POST_CONTENT);
  }

  public static Post create() {
    return create(CHANNEL_ID, POST_CONTENT);
  }

  public static Post create(Long channelId, PostContent postContent) {
    return Post.builder()
        .id(ID)
        .channelId(channelId)
        .postContent(postContent)
        .createdAt(CREATED_AT)
        .viewCount(VIEW_COUNT)
        .likeCount(LIKE_COUNT)
        .updatedAt(UPDATED_AT)
        .build();
  }

  public static Post createWithTitle(String changedTitle) {
    var postContent = new PostContent(changedTitle, CONTENT);
    return create(postContent);
  }

  public static Post createWithContent(String changedContent) {
    var postContent = new PostContent(TITLE, changedContent);
    return create(postContent);
  }

  public static Post create(PostContent postContent) {
    return create(CHANNEL_ID, postContent);
  }

  public static WritePostRequest createWriteRequest() {
    return new WritePostRequest(CHANNEL_ID, TITLE, CONTENT);
  }

  public static PostContent createNewPostContent(String newTitle, String newContent) {
    return new PostContent(newTitle, newContent);
  }
}
