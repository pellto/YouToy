package com.pellto.youtoy.domain.community.util;

import com.pellto.youtoy.domain.community.domain.CommunityComment;
import com.pellto.youtoy.domain.community.domain.CommunityPost;
import com.pellto.youtoy.domain.community.domain.PostReplyComment;
import com.pellto.youtoy.domain.community.dto.CommunityCommentDto;
import com.pellto.youtoy.domain.community.dto.ModifyCommentRequest;
import com.pellto.youtoy.domain.community.dto.WriteCommentRequest;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommunityCommentFactory {

  private static final Long ID = 1L;
  private static final CommunityPost COMMUNITY_POST = CommunityPostFactory.createPost();
  private static final UserUUID COMMENTER_UUID = new UserUUID("commenter_uuid");
  private static final String CONTENT = "content";
  private static final Long LIKE_COUNT = 0L;
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();
  private static final boolean MODIFIED = false;
  private static final LocalDateTime MODIFIED_AT = CREATED_AT;
  private static final List<PostReplyComment> REPLIES = new ArrayList<>();

  public static CommunityCommentDto createCommunityCommentDto() {
    return new CommunityCommentDto(
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

  public static CommunityCommentDto createCommunityCommentDto(String content) {
    return new CommunityCommentDto(
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

  public static ModifyCommentRequest createModifyCommentRequest(Long id, String content) {
    return new ModifyCommentRequest(id, content);
  }

  public static WriteCommentRequest createWriteCommentRequest() {
    return new WriteCommentRequest(COMMUNITY_POST.getId(),
        CONTENT, COMMENTER_UUID.getValue());
  }

  public static CommunityComment createBeforeSavedCommunityComment() {
    return CommunityComment.builder()
        .commenterUuid(COMMENTER_UUID)
        .content(CONTENT)
        .communityPost(COMMUNITY_POST)
        .build();
  }

  public static CommunityComment createBeforeSavedCommunityComment(CommunityPost post) {
    return CommunityComment.builder()
        .commenterUuid(COMMENTER_UUID)
        .content(CONTENT)
        .communityPost(post)
        .build();
  }

  public static CommunityComment createCommunityComment() {
    return CommunityComment.builder()
        .id(ID)
        .likeCount(LIKE_COUNT)
        .commenterUuid(COMMENTER_UUID)
        .communityPost(COMMUNITY_POST)
        .content(CONTENT)
        .createdAt(CREATED_AT)
        .modified(MODIFIED)
        .modifiedAt(MODIFIED_AT)
        .build();
  }

  public static CommunityComment createCommunityComment(CommunityPost post) {
    return CommunityComment.builder()
        .id(ID)
        .likeCount(LIKE_COUNT)
        .commenterUuid(COMMENTER_UUID)
        .communityPost(post)
        .content(CONTENT)
        .createdAt(CREATED_AT)
        .modified(MODIFIED)
        .modifiedAt(MODIFIED_AT)
        .build();
  }
}
