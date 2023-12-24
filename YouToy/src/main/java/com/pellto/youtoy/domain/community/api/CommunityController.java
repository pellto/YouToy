package com.pellto.youtoy.domain.community.api;

import com.pellto.youtoy.domain.community.application.CommentPostReadService;
import com.pellto.youtoy.domain.community.application.CommunityCommentReadService;
import com.pellto.youtoy.domain.community.application.CommunityCommentWriteService;
import com.pellto.youtoy.domain.community.application.CommunityPostWriteService;
import com.pellto.youtoy.domain.community.dto.CommunityCommentDto;
import com.pellto.youtoy.domain.community.dto.CommunityPostDto;
import com.pellto.youtoy.domain.community.dto.ModifyCommentRequest;
import com.pellto.youtoy.domain.community.dto.WriteCommentRequest;
import com.pellto.youtoy.domain.community.dto.WritePostRequest;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/communities")
@RequiredArgsConstructor
@RestController
public class CommunityController {

  private final CommunityCommentReadService commentReadService;
  private final CommentPostReadService postReadService;
  private final CommunityCommentWriteService commentWriteService;
  private final CommunityPostWriteService postWriteService;

  @GetMapping("/comments")
  public List<CommunityCommentDto> findAllComments() {
    return commentReadService.findAll();
  }

  @GetMapping("/comments/{id}")
  public CommunityCommentDto findCommentById(@PathVariable Long id) {
    return commentReadService.findById(id);
  }

  @PostMapping("/comments")
  public CommunityCommentDto comment(@RequestBody @Valid WriteCommentRequest req) {
    return commentWriteService.write(req);
  }

  @PatchMapping("/comments")
  public CommunityCommentDto modifyComment(@RequestBody @Valid ModifyCommentRequest req) {
    return commentWriteService.modify(req);
  }

  @DeleteMapping("/comments/{id}")
  public void deleteCommentById(@PathVariable Long id) {
    commentWriteService.deleteById(id);
  }

  @GetMapping("/posts")
  public List<CommunityPostDto> findAllPosts() {
    return postReadService.findAll();
  }

  @GetMapping("/posts/{id}")
  public CommunityPostDto findPostById(@PathVariable Long id) {
    return postReadService.findById(id);
  }

  @PostMapping("/posts")
  public CommunityPostDto writePost(@RequestBody @Valid WritePostRequest req) {
    return postWriteService.writePost(req);
  }

  @DeleteMapping("/posts/{id}")
  public void deletePostById(@PathVariable Long id) {
    postWriteService.deleteById(id);
  }
}
