package com.pellto.youtoy.domain.video.util;

import com.pellto.youtoy.domain.base.dto.WriteCommentRequest;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import com.pellto.youtoy.domain.video.domain.Video;
import com.pellto.youtoy.domain.video.domain.VideoComment;
import com.pellto.youtoy.domain.video.domain.VideoReplyComment;
import com.pellto.youtoy.domain.video.dto.VideoCommentDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VideoCommentFactory {

  private static final Long ID = 1L;
  private static final Video CONTENT = VideoFactory.create();
  private static final UserUUID COMMENTER = new UserUUID("test-commenter");
  private static final String COMMENT_CONTENT = "test-comment";
  private static final Long LIKE_COUNT = 0L;
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();
  private static final boolean MODIFIED = false;
  private static final LocalDateTime MODIFIED_AT = CREATED_AT;
  private static final List<VideoReplyComment> REPLIES = new ArrayList<>();

  public static VideoComment create(WriteCommentRequest req) {
    return VideoComment.builder()
        .id(ID)
        .contents(VideoFactory.create(req.contentId()))
        .commenterUuid(new UserUUID(req.commenterUuid()))
        .commentContent(req.content())
        .likeCount(LIKE_COUNT)
        .createdAt(CREATED_AT)
        .modified(MODIFIED)
        .modifiedAt(MODIFIED_AT)
        .build();
  }

  public static VideoCommentDto createCommentDto() {
    return new VideoCommentDto(ID, CONTENT.getId(), COMMENTER.getValue(), LIKE_COUNT,
        COMMENT_CONTENT, REPLIES.size(), MODIFIED, CREATED_AT);
  }

  public static VideoComment create() {
    return VideoComment.builder()
        .id(ID)
        .contents(CONTENT)
        .commenterUuid(COMMENTER)
        .commentContent(COMMENT_CONTENT)
        .likeCount(LIKE_COUNT)
        .createdAt(CREATED_AT)
        .modified(MODIFIED)
        .modifiedAt(MODIFIED_AT)
        .build();
  }

  public static VideoComment createBeforeSaved() {
    return VideoComment.builder()
        .contents(CONTENT)
        .commenterUuid(COMMENTER)
        .commentContent(COMMENT_CONTENT)
        .build();
  }

  public static VideoComment createBeforeSaved(Video video) {
    return VideoComment.builder()
        .contents(video)
        .commenterUuid(COMMENTER)
        .commentContent(COMMENT_CONTENT)
        .build();
  }

  public static VideoCommentDto createCommentDto(VideoComment comment) {
    return new VideoCommentDto(comment.getId(), comment.getContents().getId(),
        comment.getCommenterUuid().getValue(), comment.getLikeCount(),
        comment.getCommentContent(), comment.getReplyCount(), comment.isModified(),
        comment.getCreatedAt());
  }

  public static WriteCommentRequest createWriteCommentRequest(Long contentId) {
    return new WriteCommentRequest(contentId, COMMENT_CONTENT, COMMENTER.getValue());
  }
}
