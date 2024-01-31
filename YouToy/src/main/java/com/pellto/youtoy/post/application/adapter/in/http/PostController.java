package com.pellto.youtoy.post.application.adapter.in.http;

import com.pellto.youtoy.global.dto.post.PostDto;
import com.pellto.youtoy.global.dto.post.request.ChangePostRequest;
import com.pellto.youtoy.global.dto.post.request.WritePostRequest;
import com.pellto.youtoy.post.domain.port.in.ChangePostContentUsecase;
import com.pellto.youtoy.post.domain.port.in.ReadPostUsecase;
import com.pellto.youtoy.post.domain.port.in.WritePostUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

  private final WritePostUsecase writePostUsecase;
  private final ReadPostUsecase readPostUsecase;
  private final ChangePostContentUsecase changePostContentUsecase;

  @GetMapping("/exist/{postId}")
  public boolean isExistPost(@PathVariable Long postId) {
    return readPostUsecase.isExistPost(postId);
  }

  @PostMapping
  public PostDto writePost(@RequestBody WritePostRequest request) {
    return writePostUsecase.write(request);
  }

  @PatchMapping("/{postId}/title")
  public PostDto changePostTitle(@PathVariable Long postId, @RequestBody String title) {
    return changePostContentUsecase.changePostTitle(postId, title);
  }

  @PatchMapping("/{postId}/content")
  public PostDto changePostContent(@PathVariable Long postId, @RequestBody String content) {
    return changePostContentUsecase.changePostContent(postId, content);
  }

  @PatchMapping("/{postId}")
  public PostDto changePostContent(@RequestBody ChangePostRequest request) {
    return changePostContentUsecase.changePost(request.id(), request.postContent());
  }
}
