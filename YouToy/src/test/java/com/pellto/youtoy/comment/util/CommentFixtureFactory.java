package com.pellto.youtoy.comment.util;

import com.pellto.youtoy.channel.util.ChannelFixtureFactory;
import com.pellto.youtoy.comment.domain.model.Comment;
import com.pellto.youtoy.comment.domain.model.CommentContentsType;
import com.pellto.youtoy.comment.domain.model.CommenterInfo;
import com.pellto.youtoy.global.dto.channel.response.GetCommenterChannelInfoResponse;
import com.pellto.youtoy.global.dto.comment.request.ChangeCommentRequest;
import com.pellto.youtoy.global.dto.comment.request.WriteCommentRequest;
import java.time.LocalDateTime;

public class CommentFixtureFactory {

  private static final Long ID = 1L;
  private static final Long CONTENTS_ID = 1L;
  private static final String COMMENT_CONTENTS_TYPE = CommentContentsType.POST.getType();
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();
  private static final CommenterInfo COMMENTER_INFO = ChannelFixtureFactory.createCommenterInfo();
  private static final String CONTENT = "content";
  private static final boolean OWNER_LIKE = false;
  private static final Long REPLY_COUNT = 0L;
  private static final Long LIKE_COUNT = 0L;
  private static final LocalDateTime UPDATED_AT = CREATED_AT;
  private static final String CHANGED_CONTENT = "NewContent";

  public static Comment create(Comment beforeSaved) {
    return Comment.builder()
        .id(ID)
        .contentsId(beforeSaved.getContentsId())
        .commentContentsType(beforeSaved.getCommentContentsType().getType())
        .createdAt(CREATED_AT)
        .commenterInfo(beforeSaved.getCommenterInfo())
        .content(beforeSaved.getContent())
        .ownerLike(OWNER_LIKE)
        .replyCount(REPLY_COUNT)
        .likeCount(LIKE_COUNT)
        .updatedAt(UPDATED_AT)
        .build();
  }

  public static Comment create(Long commentId) {
    return Comment.builder()
        .id(commentId)
        .contentsId(CONTENTS_ID)
        .commentContentsType(COMMENT_CONTENTS_TYPE)
        .createdAt(CREATED_AT)
        .commenterInfo(COMMENTER_INFO)
        .content(CONTENT)
        .ownerLike(OWNER_LIKE)
        .replyCount(REPLY_COUNT)
        .likeCount(LIKE_COUNT)
        .updatedAt(UPDATED_AT)
        .build();
  }

  public static Comment createWithLikeCount(Long likeCount) {
    return Comment.builder()
        .id(ID)
        .contentsId(CONTENTS_ID)
        .commentContentsType(COMMENT_CONTENTS_TYPE)
        .createdAt(CREATED_AT)
        .commenterInfo(COMMENTER_INFO)
        .content(CONTENT)
        .ownerLike(OWNER_LIKE)
        .replyCount(REPLY_COUNT)
        .likeCount(likeCount)
        .updatedAt(UPDATED_AT)
        .build();
  }

  public static Comment createBeforeSaved() {
    return Comment.builder()
        .contentsId(CONTENTS_ID)
        .commentContentsType(COMMENT_CONTENTS_TYPE)
        .commenterInfo(COMMENTER_INFO)
        .content(CONTENT)
        .build();
  }

  public static Comment create() {
    return Comment.builder()
        .id(ID)
        .contentsId(CONTENTS_ID)
        .commentContentsType(COMMENT_CONTENTS_TYPE)
        .createdAt(CREATED_AT)
        .commenterInfo(COMMENTER_INFO)
        .content(CONTENT)
        .ownerLike(OWNER_LIKE)
        .replyCount(REPLY_COUNT)
        .likeCount(LIKE_COUNT)
        .updatedAt(UPDATED_AT)
        .build();
  }

  public static Comment create(String commentContentsType) {
    return Comment.builder()
        .id(ID)
        .contentsId(CONTENTS_ID)
        .commentContentsType(commentContentsType)
        .createdAt(CREATED_AT)
        .commenterInfo(COMMENTER_INFO)
        .content(CONTENT)
        .ownerLike(OWNER_LIKE)
        .replyCount(REPLY_COUNT)
        .likeCount(LIKE_COUNT)
        .updatedAt(UPDATED_AT)
        .build();
  }

  public static Comment create(String commentContentsType, Long id) {
    return Comment.builder()
        .id(id)
        .contentsId(CONTENTS_ID)
        .commentContentsType(commentContentsType)
        .createdAt(CREATED_AT)
        .commenterInfo(COMMENTER_INFO)
        .content(CONTENT)
        .ownerLike(OWNER_LIKE)
        .replyCount(REPLY_COUNT)
        .likeCount(LIKE_COUNT)
        .updatedAt(UPDATED_AT)
        .build();
  }

  public static ChangeCommentRequest createChangeCommentRequest() {
    return new ChangeCommentRequest(ID, CHANGED_CONTENT);
  }

  public static Comment createWithReplyCount(Long replyCount) {
    return Comment.builder()
        .id(ID)
        .contentsId(CONTENTS_ID)
        .commentContentsType(COMMENT_CONTENTS_TYPE)
        .createdAt(CREATED_AT)
        .commenterInfo(COMMENTER_INFO)
        .content(CONTENT)
        .ownerLike(OWNER_LIKE)
        .replyCount(replyCount)
        .likeCount(LIKE_COUNT)
        .updatedAt(UPDATED_AT)
        .build();
  }

  public static WriteCommentRequest createWriteCommentRequest() {
    return new WriteCommentRequest(1L, 1L, COMMENT_CONTENTS_TYPE, CONTENT);
  }

  public static GetCommenterChannelInfoResponse createGetCommenterChannelInfoResponse() {
    return GetCommenterChannelInfoResponse.builder()
        .commenterId(COMMENTER_INFO.commenterId())
        .commenterHandle(COMMENTER_INFO.commenterHandle())
        .displayName(COMMENTER_INFO.displayName())
        .profilePath(COMMENTER_INFO.profilePath())
        .build();
  }

  public static Comment createChangedComment() {
    return Comment.builder()
        .id(ID)
        .contentsId(CONTENTS_ID)
        .commentContentsType(COMMENT_CONTENTS_TYPE)
        .createdAt(CREATED_AT)
        .commenterInfo(COMMENTER_INFO)
        .content(CHANGED_CONTENT)
        .ownerLike(OWNER_LIKE)
        .replyCount(REPLY_COUNT)
        .likeCount(LIKE_COUNT)
        .updatedAt(UPDATED_AT)
        .build();
  }
}
