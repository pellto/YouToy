package com.pellto.youtoy.domain.video.util;

import com.pellto.youtoy.domain.base.dto.WriteCommentRequest;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import com.pellto.youtoy.domain.video.domain.VideoComment;
import com.pellto.youtoy.domain.video.domain.VideoReply;
import com.pellto.youtoy.domain.video.dto.VideoReplyDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VideoReplyFactory {

  private static final Long ID = 1L;
  private static final VideoComment CONTENT = VideoCommentFactory.create();
  private static final UserUUID COMMENTER = new UserUUID("test-commenter");
  private static final String COMMENT_CONTENT = "test-comment";
  private static final int LIKE_COUNT = 0;
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();
  private static final boolean MODIFIED = false;
  private static final LocalDateTime MODIFIED_AT = CREATED_AT;
  private static final List<VideoReply> REPLIES = new ArrayList<>();

  public static VideoReply create(WriteCommentRequest req) {
    return VideoReply.builder()
        .id(ID)
        .parentComment(VideoCommentFactory.create(req.contentId()))
        .commenterUuid(new UserUUID(req.commenterUuid()))
        .content(req.content())
        .createdAt(CREATED_AT)
        .modified(MODIFIED)
        .modifiedAt(MODIFIED_AT)
        .build();
  }

  public static VideoReply create(Long replyId) {
    return VideoReply.builder()
        .id(replyId)
        .parentComment(CONTENT)
        .commenterUuid(COMMENTER)
        .content(COMMENT_CONTENT)
        .createdAt(CREATED_AT)
        .modified(MODIFIED)
        .modifiedAt(MODIFIED_AT)
        .build();
  }

  public static VideoReply create(VideoComment parentComment) {
    return VideoReply.builder()
        .id(ID)
        .parentComment(parentComment)
        .commenterUuid(COMMENTER)
        .content(COMMENT_CONTENT)
        .createdAt(CREATED_AT)
        .modified(MODIFIED)
        .modifiedAt(MODIFIED_AT)
        .build();
  }

  public static VideoReplyDto createDto() {
    return new VideoReplyDto(ID, CONTENT.getId(), COMMENTER.getValue(), LIKE_COUNT,
        COMMENT_CONTENT, MODIFIED, CREATED_AT);
  }

  public static VideoReply create() {
    return VideoReply.builder()
        .id(ID)
        .parentComment(CONTENT)
        .commenterUuid(COMMENTER)
        .content(COMMENT_CONTENT)
        .createdAt(CREATED_AT)
        .modified(MODIFIED)
        .modifiedAt(MODIFIED_AT)
        .build();
  }

  public static VideoReply createBeforeSaved() {
    return VideoReply.builder()
        .parentComment(CONTENT)
        .commenterUuid(COMMENTER)
        .content(COMMENT_CONTENT)
        .build();
  }

  public static VideoReply createBeforeSaved(VideoComment parentComment) {
    return VideoReply.builder()
        .parentComment(parentComment)
        .commenterUuid(COMMENTER)
        .content(COMMENT_CONTENT)
        .build();
  }

  public static VideoReplyDto createDto(VideoReply reply) {
    return new VideoReplyDto(reply.getId(), reply.getParentComment().getId(),
        reply.getCommenterUuid().getValue(), reply.getLikeCount(),
        reply.getCommentContent(), reply.isModified(),
        reply.getCreatedAt());
  }

  public static WriteCommentRequest createWriteRequest(Long contentId) {
    return new WriteCommentRequest(contentId, COMMENT_CONTENT, COMMENTER.getValue());
  }
}
