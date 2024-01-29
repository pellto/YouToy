package com.pellto.youtoy.post.application.adapter.out.persistence;

import com.pellto.youtoy.post.domain.model.Post;
import com.pellto.youtoy.post.domain.model.PostContent;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

  public Post toDomain(PostEntity entity) {
    var postContent = PostContent.builder()
        .title(entity.getTitle())
        .content(entity.getContent())
        .build();
    return Post.builder()
        .id(entity.getId())
        .channelId(entity.getChannelId())
        .postContent(postContent)
        .createdAt(entity.getCreatedAt())
        .updatedAt(entity.getUpdatedAt())
        .viewCount(entity.getViewCount())
        .likeCount(entity.getLikeCount())
        .build();
  }

  public PostEntity toEntity(Post post) {
    return PostEntity.builder()
        .id(post.getId())
        .channelId(post.getChannelId())
        .content(post.getPostContent().getContent())
        .title(post.getPostContent().getTitle())
        .createdAt(post.getCreatedAt())
        .updatedAt(post.getUpdatedAt())
        .viewCount(post.getViewCount())
        .likeCount(post.getLikeCount())
        .build();
  }
}
