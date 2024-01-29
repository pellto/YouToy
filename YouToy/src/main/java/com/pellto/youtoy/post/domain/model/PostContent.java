package com.pellto.youtoy.post.domain.model;

import java.util.Objects;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostContent {

  private final String title;
  private final String content;

  @Builder
  public PostContent(String title, String content) {
    this.title = Objects.requireNonNull(title);
    this.content = Objects.requireNonNull(content);
  }
}
