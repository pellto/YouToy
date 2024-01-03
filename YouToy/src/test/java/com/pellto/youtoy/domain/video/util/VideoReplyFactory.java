package com.pellto.youtoy.domain.video.util;

import com.pellto.youtoy.domain.base.dto.WriteCommentRequest;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import com.pellto.youtoy.domain.video.domain.VideoComment;
import com.pellto.youtoy.domain.video.domain.VideoReplyComment;
import com.pellto.youtoy.domain.video.dto.VideoReplyCommentDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VideoReplyFactory {

  private static final Long ID = 1L;
  private static final VideoComment CONTENT = VideoCommentFactory.create();
  private static final UserUUID COMMENTER = new UserUUID("test-commenter");
  private static final String COMMENT_CONTENT = "test-comment";
  private static final Long LIKE_COUNT = 0L;
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();
  private static final boolean MODIFIED = false;
  private static final LocalDateTime MODIFIED_AT = CREATED_AT;
  private static final List<VideoReplyComment> REPLIES = new ArrayList<>();

  public static VideoReplyComment create(WriteCommentRequest req) {
    return VideoReplyComment.builder()
        .id(ID)
        .parentComment(VideoCommentFactory.create(req.contentId()))
        .commenterUuid(new UserUUID(req.commenterUuid()))
        .content(req.content())
        .likeCount(LIKE_COUNT)
        .createdAt(CREATED_AT)
        .modified(MODIFIED)
        .modifiedAt(MODIFIED_AT)
        .build();
  }

  public static VideoReplyCommentDto createDto() {
    return new VideoReplyCommentDto(ID, CONTENT.getId(), COMMENTER.getValue(), LIKE_COUNT,
        COMMENT_CONTENT, MODIFIED, CREATED_AT);
  }

  public static VideoReplyComment create() {
    return VideoReplyComment.builder()
        .id(ID)
        .parentComment(CONTENT)
        .commenterUuid(COMMENTER)
        .content(COMMENT_CONTENT)
        .likeCount(LIKE_COUNT)
        .createdAt(CREATED_AT)
        .modified(MODIFIED)
        .modifiedAt(MODIFIED_AT)
        .build();
  }

  public static VideoReplyComment createBeforeSaved() {
    return VideoReplyComment.builder()
        .parentComment(CONTENT)
        .commenterUuid(COMMENTER)
        .content(COMMENT_CONTENT)
        .build();
  }

  public static VideoReplyComment createBeforeSaved(VideoComment parentComment) {
    return VideoReplyComment.builder()
        .parentComment(parentComment)
        .commenterUuid(COMMENTER)
        .content(COMMENT_CONTENT)
        .build();
  }

  public static VideoReplyCommentDto createDto(VideoReplyComment reply) {
    return new VideoReplyCommentDto(reply.getId(), reply.getParentComment().getId(),
        reply.getCommenterUuid().getValue(), reply.getLikeCount(),
        reply.getCommentContent(), reply.isModified(),
        reply.getCreatedAt());
  }

  public static WriteCommentRequest createWriteRequest(Long contentId) {
    return new WriteCommentRequest(contentId, COMMENT_CONTENT, COMMENTER.getValue());
  }
}
