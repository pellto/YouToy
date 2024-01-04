package com.pellto.youtoy.domain.post.util;

import com.pellto.youtoy.domain.base.dto.WriteCommentRequest;
import com.pellto.youtoy.domain.post.domain.PostComment;
import com.pellto.youtoy.domain.post.domain.PostReply;
import com.pellto.youtoy.domain.post.dto.PostReplyDto;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import java.time.LocalDateTime;

public class PostReplyFactory {

  private static final Long ID = 1L;
  private static final PostComment PARENT_COMMENT = PostCommentFactory.createCommunityComment();
  private static final UserUUID COMMENTER_UUID = new UserUUID("commenter_uuid");
  private static final String CONTENT = "content";
  private static final int LIKE_COUNT = 0;
  private static final boolean MODIFIED = false;
  private static final boolean MENTIONED = false;
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();
  private static final LocalDateTime MODIFIED_AT = CREATED_AT;

  public static PostReply createBeforeSaved() {
    return PostReply.builder()
        .parentComment(PARENT_COMMENT)
        .content(CONTENT)
        .commenterUuid(COMMENTER_UUID)
        .build();
  }

  public static PostReply createBeforeSaved(PostComment parentComment) {
    return PostReply.builder()
        .parentComment(parentComment)
        .content(CONTENT)
        .commenterUuid(COMMENTER_UUID)
        .build();
  }

  public static PostReply create() {
    return PostReply.builder()
        .id(ID)
        .parentComment(PARENT_COMMENT)
        .commenterUuid(COMMENTER_UUID)
        .content(CONTENT)
        .modified(MODIFIED)
        .createdAt(CREATED_AT)
        .modifiedAt(MODIFIED_AT)
        .mentioned(MENTIONED)
        .build();
  }

  public static PostReply create(PostComment parentComment) {
    return PostReply.builder()
        .id(ID)
        .parentComment(parentComment)
        .commenterUuid(COMMENTER_UUID)
        .content(CONTENT)
        .modified(MODIFIED)
        .createdAt(CREATED_AT)
        .modifiedAt(MODIFIED_AT)
        .mentioned(MENTIONED)
        .build();
  }

  public static PostReplyDto createDto() {
    return new PostReplyDto(
        ID,
        PARENT_COMMENT.getId(),
        COMMENTER_UUID.getValue(),
        LIKE_COUNT,
        CONTENT,
        MODIFIED,
        CREATED_AT
    );
  }

  public static WriteCommentRequest createWriteReplyRequest() {
    return new WriteCommentRequest(PARENT_COMMENT.getId(), CONTENT,
        COMMENTER_UUID.getValue());
  }
}
