package com.pellto.youtoy.domain.post.util;

import com.pellto.youtoy.domain.base.dto.ModifyCommentRequest;
import com.pellto.youtoy.domain.base.dto.WriteCommentRequest;
import com.pellto.youtoy.domain.post.domain.Post;
import com.pellto.youtoy.domain.post.domain.PostComment;
import com.pellto.youtoy.domain.post.domain.PostReply;
import com.pellto.youtoy.domain.post.dto.PostCommentDto;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostCommentFactory {

  private static final Long ID = 1L;
  private static final Post COMMUNITY_POST = PostFactory.createPost();
  private static final UserUUID COMMENTER_UUID = new UserUUID("commenter_uuid");
  private static final String CONTENT = "content";
  private static final int LIKE_COUNT = 0;
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();
  private static final boolean MODIFIED = false;
  private static final LocalDateTime MODIFIED_AT = CREATED_AT;
  private static final List<PostReply> REPLIES = new ArrayList<>();

  public static PostCommentDto createCommunityCommentDto() {
    return new PostCommentDto(
        ID,
        COMMUNITY_POST.getId(),
        COMMENTER_UUID.getValue(),
        LIKE_COUNT,
        CONTENT,
        REPLIES.size(),
        MODIFIED,
        CREATED_AT
    );
  }

  public static PostCommentDto createCommunityCommentDto(String content) {
    return new PostCommentDto(
        ID,
        COMMUNITY_POST.getId(),
        COMMENTER_UUID.getValue(),
        LIKE_COUNT,
        content,
        REPLIES.size(),
        true,
        CREATED_AT
    );
  }

  public static ModifyCommentRequest createModifyCommentRequest(Long id,
      String content) {
    return new ModifyCommentRequest(id, content);
  }

  public static WriteCommentRequest createWriteCommentRequest() {
    return new WriteCommentRequest(COMMUNITY_POST.getId(),
        CONTENT, COMMENTER_UUID.getValue());
  }

  public static PostComment createBeforeSavedCommunityComment() {
    return PostComment.builder()
        .commenterUuid(COMMENTER_UUID)
        .commentContent(CONTENT)
        .contents(COMMUNITY_POST)
        .build();
  }

  public static PostComment createBeforeSavedCommunityComment(Post post) {
    return PostComment.builder()
        .commenterUuid(COMMENTER_UUID)
        .commentContent(CONTENT)
        .contents(post)
        .build();
  }

  public static PostComment createCommunityComment() {
    return PostComment.builder()
        .id(ID)
        .commenterUuid(COMMENTER_UUID)
        .contents(COMMUNITY_POST)
        .commentContent(CONTENT)
        .createdAt(CREATED_AT)
        .modified(MODIFIED)
        .modifiedAt(MODIFIED_AT)
        .build();
  }

  public static PostComment createCommunityComment(Post post) {
    return PostComment.builder()
        .id(ID)
        .commenterUuid(COMMENTER_UUID)
        .contents(post)
        .commentContent(CONTENT)
        .createdAt(CREATED_AT)
        .modified(MODIFIED)
        .modifiedAt(MODIFIED_AT)
        .build();
  }
}
