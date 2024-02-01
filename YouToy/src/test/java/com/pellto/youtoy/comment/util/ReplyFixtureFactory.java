package com.pellto.youtoy.comment.util;

import com.pellto.youtoy.channel.util.ChannelFixtureFactory;
import com.pellto.youtoy.comment.domain.model.CommenterInfo;
import com.pellto.youtoy.comment.domain.model.Reply;
import com.pellto.youtoy.global.dto.channel.response.GetCommenterChannelInfoResponse;
import com.pellto.youtoy.global.dto.comment.request.WriteReplyRequest;
import java.time.LocalDateTime;

public class ReplyFixtureFactory {

  private static final Long ID = 1L;
  private static final Long PARENT_COMMENT_ID = 1L;
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();
  private static final CommenterInfo REPLIER_INFO = ChannelFixtureFactory.createCommenterInfo(2L);
  private static final String CONTENT = "REPLY CONTENT";
  private static final Long LIKE_COUNT = 0L;
  private static final boolean OWNER_LIKE = false;
  private static final LocalDateTime UPDATED_AT = LocalDateTime.now();

  public static Reply create(Long id) {
    return Reply.builder()
        .id(id)
        .parentCommentId(PARENT_COMMENT_ID)
        .createdAt(CREATED_AT)
        .replierInfo(REPLIER_INFO)
        .content(CONTENT)
        .likeCount(LIKE_COUNT)
        .ownerLike(OWNER_LIKE)
        .updatedAt(UPDATED_AT)
        .build();
  }

  public static Reply create(WriteReplyRequest request) {
    var replierInfo = CommenterInfo.builder()
        .commenterId(request.replierId())
        .commenterHandle(REPLIER_INFO.commenterHandle())
        .displayName(REPLIER_INFO.displayName())
        .profilePath(REPLIER_INFO.profilePath())
        .build();
    return Reply.builder()
        .id(ID)
        .parentCommentId(request.parentCommentId())
        .createdAt(CREATED_AT)
        .replierInfo(replierInfo)
        .content(request.content())
        .likeCount(LIKE_COUNT)
        .ownerLike(OWNER_LIKE)
        .updatedAt(UPDATED_AT)
        .build();
  }


  public static Reply createBeforeSaved() {
    return Reply.builder()
        .parentCommentId(PARENT_COMMENT_ID)
        .replierInfo(REPLIER_INFO)
        .content(CONTENT)
        .build();
  }

  public static Reply create() {
    return Reply.builder()
        .id(ID)
        .parentCommentId(PARENT_COMMENT_ID)
        .createdAt(CREATED_AT)
        .replierInfo(REPLIER_INFO)
        .content(CONTENT)
        .likeCount(LIKE_COUNT)
        .ownerLike(OWNER_LIKE)
        .updatedAt(UPDATED_AT)
        .build();
  }

  public static GetCommenterChannelInfoResponse createGetCommenterChannelInfoResponse(
      WriteReplyRequest request) {
    return new GetCommenterChannelInfoResponse(
        request.replierId(),
        REPLIER_INFO.commenterHandle(),
        REPLIER_INFO.displayName(),
        REPLIER_INFO.profilePath()
    );
  }

  public static WriteReplyRequest createWriteReplyRequest() {
    return new WriteReplyRequest(REPLIER_INFO.commenterId(), PARENT_COMMENT_ID, CONTENT);
  }
}
