package com.pellto.youtoy.comment.application.adapter.out.persistence.reply;

import com.pellto.youtoy.comment.domain.model.CommenterInfo;
import com.pellto.youtoy.comment.domain.model.Reply;
import org.springframework.stereotype.Component;

@Component
public class ReplyMapper {

  public Reply toDomain(ReplyEntity entity) {
    var replierInfo = CommenterInfo.builder()
        .commenterId(entity.getReplierId())
        .commenterHandle(entity.getReplierHandle())
        .displayName(entity.getReplierDisplayName())
        .profilePath(entity.getReplierProfilePath())
        .build();
    return Reply.builder()
        .id(entity.getId())
        .parentCommentId(entity.getParentCommentId())
        .createdAt(entity.getCreatedAt())
        .replierInfo(replierInfo)
        .content(entity.getContent())
        .likeCount(entity.getLikeCount())
        .ownerLike(entity.isOwnerLike())
        .updatedAt(entity.getUpdatedAt())
        .build();
  }

  public ReplyEntity toEntity(Reply reply) {
    return ReplyEntity.builder()
        .id(reply.getId())
        .parentCommentId(reply.getParentCommentId())
        .createdAt(reply.getCreatedAt())
        .replierId(reply.getReplierInfo().commenterId())
        .replierHandle(reply.getReplierInfo().commenterHandle())
        .replierDisplayName(reply.getReplierInfo().displayName())
        .replierProfilePath(reply.getReplierInfo().profilePath())
        .content(reply.getContent())
        .likeCount(reply.getLikeCount())
        .ownerLike(reply.isOwnerLike())
        .updatedAt(reply.getUpdatedAt())
        .build();
  }
}
