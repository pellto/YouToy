package com.pellto.youtoy.domain.community.util;

import com.pellto.youtoy.domain.community.domain.CommunityComment;
import com.pellto.youtoy.domain.community.domain.PostReplyComment;
import com.pellto.youtoy.domain.community.dto.PostReplyCommentDto;
import com.pellto.youtoy.domain.community.dto.WriteReplyRequest;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import java.time.LocalDateTime;

public class PostReplyCommentFactory {

  private static final Long ID = 1L;
  private static final CommunityComment PARENT_COMMENT = CommunityCommentFactory.createCommunityComment();
  private static final UserUUID COMMENTER_UUID = new UserUUID("commenter_uuid");
  private static final String CONTENT = "content";
  private static final Long LIKE_COUNT = 0L;
  private static final boolean MODIFIED = false;
  private static final boolean MENTIONED = false;
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();
  private static final LocalDateTime MODIFIED_AT = CREATED_AT;

  public static PostReplyComment createBeforeSavedReplyComment() {
    return PostReplyComment.builder()
        .parentComment(PARENT_COMMENT)
        .content(CONTENT)
        .commenterUuid(COMMENTER_UUID)
        .build();
  }

  public static PostReplyComment createBeforeSavedReplyComment(CommunityComment parentComment) {
    return PostReplyComment.builder()
        .parentComment(parentComment)
        .content(CONTENT)
        .commenterUuid(COMMENTER_UUID)
        .build();
  }

  public static PostReplyComment createReplyComment() {
    return PostReplyComment.builder()
        .id(ID)
        .parentComment(PARENT_COMMENT)
        .commenterUuid(COMMENTER_UUID)
        .likeCount(LIKE_COUNT)
        .content(CONTENT)
        .modified(MODIFIED)
        .createdAt(CREATED_AT)
        .modifiedAt(MODIFIED_AT)
        .mentioned(MENTIONED)
        .build();
  }

  public static PostReplyComment createReplyComment(CommunityComment parentComment) {
    return PostReplyComment.builder()
        .id(ID)
        .parentComment(parentComment)
        .commenterUuid(COMMENTER_UUID)
        .likeCount(LIKE_COUNT)
        .content(CONTENT)
        .modified(MODIFIED)
        .createdAt(CREATED_AT)
        .modifiedAt(MODIFIED_AT)
        .mentioned(MENTIONED)
        .build();
  }

  public static PostReplyCommentDto createReplyCommentDto() {
    return new PostReplyCommentDto(
        ID,
        PARENT_COMMENT.getId(),
        COMMENTER_UUID.getValue(),
        LIKE_COUNT,
        CONTENT,
        MODIFIED,
        CREATED_AT
    );
  }

  public static WriteReplyRequest createWriteReplyRequest() {
    return new WriteReplyRequest(PARENT_COMMENT.getId(), CONTENT, COMMENTER_UUID.getValue());
  }
}
