package com.pellto.youtoy.domain.community.util;

import com.pellto.youtoy.domain.community.domain.CommunityComment;
import com.pellto.youtoy.domain.community.dto.CommunityCommentDto;
import com.pellto.youtoy.domain.community.dto.ModifyCommentRequest;
import com.pellto.youtoy.domain.community.dto.WriteCommentRequest;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import java.time.LocalDateTime;

public class CommentUtil {

  private static final Long ID = 1L;
  private static final Long COMMUNITY_POST_ID = 1L;
  private static final UserUUID COMMENTER_UUID = new UserUUID("commenter_uuid");
  private static final String CONTENT = "content";
  private static final Long LIKE_COUNT = 0L;
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();
  private static final boolean MODIFIED = false;
  private static final LocalDateTime MODIFIED_AT = CREATED_AT;

  public static CommunityCommentDto createCommunityCommentDto() {
    return new CommunityCommentDto(
        ID,
        COMMUNITY_POST_ID,
        COMMENTER_UUID.getValue(),
        LIKE_COUNT,
        CONTENT,
        MODIFIED,
        CREATED_AT
    );
  }

  public static CommunityCommentDto createCommunityCommentDto(String content) {
    return new CommunityCommentDto(
        ID,
        COMMUNITY_POST_ID,
        COMMENTER_UUID.getValue(),
        LIKE_COUNT,
        content,
        true,
        CREATED_AT
    );
  }

  public static ModifyCommentRequest createModifyCommentRequest(Long id, String content) {
    return new ModifyCommentRequest(id, content);
  }

  public static WriteCommentRequest createWriteCommentRequest() {
    return new WriteCommentRequest(COMMUNITY_POST_ID,
        CONTENT, COMMENTER_UUID.getValue());
  }

  public static CommunityComment createBeforeSavedCommunityComment() {
    return CommunityComment.builder()
        .commenterUuid(COMMENTER_UUID)
        .content(CONTENT)
        .communityPostId(COMMUNITY_POST_ID)
        .build();
  }

  public static CommunityComment createCommunityComment() {
    return CommunityComment.builder()
        .id(ID)
        .likeCount(LIKE_COUNT)
        .commenterUuid(COMMENTER_UUID)
        .communityPostId(COMMUNITY_POST_ID)
        .content(CONTENT)
        .createdAt(CREATED_AT)
        .modified(MODIFIED)
        .modifiedAt(MODIFIED_AT)
        .build();
  }
}
