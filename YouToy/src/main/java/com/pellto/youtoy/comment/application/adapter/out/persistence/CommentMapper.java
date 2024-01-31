package com.pellto.youtoy.comment.application.adapter.out.persistence;

import com.pellto.youtoy.comment.domain.model.Comment;
import com.pellto.youtoy.comment.domain.model.CommenterInfo;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

  public Comment toDomain(CommentEntity entity) {
    var commenterInfo = CommenterInfo.builder()
        .commenterId(entity.getCommenterId())
        .commenterHandle(entity.getCommenterHandle())
        .displayName(entity.getCommenterDisplayName())
        .profilePath(entity.getCommenterProfilePath())
        .build();

    //(entity.getCommenterId(), entity.getCommenterHandle());
    return Comment.builder()
        .id(entity.getId())
        .commenterInfo(commenterInfo)
        .contentsId(entity.getContentsId())
        .commentContentsType(entity.getCommentContentsType())
        .content(entity.getContent())
        .ownerLike(entity.isOwnerLike())
        .replyCount(entity.getReplyCount())
        .likeCount(entity.getLikeCount())
        .createdAt(entity.getCreatedAt())
        .updatedAt(entity.getUpdatedAt())
        .build();
  }

  public CommentEntity toEntity(Comment comment) {
    return CommentEntity.builder()
        .id(comment.getId())
        .commenterId(comment.getCommenterInfo().commenterId())
        .commenterHandle(comment.getCommenterInfo().commenterHandle())
        .commenterDisplayName(comment.getCommenterInfo().displayName())
        .commenterProfilePath(comment.getCommenterInfo().profilePath())
        .contentsId(comment.getContentsId())
        .commentContentsType(comment.getCommentContentsType().getType())
        .content(comment.getContent())
        .ownerLike(comment.isOwnerLike())
        .replyCount(comment.getReplyCount())
        .likeCount(comment.getLikeCount())
        .createdAt(comment.getCreatedAt())
        .updatedAt(comment.getUpdatedAt())
        .build();
  }
}
