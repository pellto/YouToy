package com.pellto.youtoy.domain.post.api;

import com.pellto.youtoy.domain.base.dto.ModifyCommentRequest;
import com.pellto.youtoy.domain.base.dto.WriteCommentRequest;
import com.pellto.youtoy.domain.base.dto.WriteInterestRequest;
import com.pellto.youtoy.domain.post.application.PostCommentInterestService;
import com.pellto.youtoy.domain.post.application.PostCommentReadService;
import com.pellto.youtoy.domain.post.application.PostCommentWriteService;
import com.pellto.youtoy.domain.post.application.PostInterestService;
import com.pellto.youtoy.domain.post.application.PostReadService;
import com.pellto.youtoy.domain.post.application.PostReplyInterestService;
import com.pellto.youtoy.domain.post.application.PostReplyReadService;
import com.pellto.youtoy.domain.post.application.PostReplyWriteService;
import com.pellto.youtoy.domain.post.application.PostWriteService;
import com.pellto.youtoy.domain.post.dto.PostCommentDto;
import com.pellto.youtoy.domain.post.dto.PostCommentInterestDto;
import com.pellto.youtoy.domain.post.dto.PostDto;
import com.pellto.youtoy.domain.post.dto.PostInterestDto;
import com.pellto.youtoy.domain.post.dto.PostReplyDto;
import com.pellto.youtoy.domain.post.dto.PostReplyInterestDto;
import com.pellto.youtoy.domain.post.dto.PostWriteRequest;
import com.pellto.youtoy.global.filter.auth.AdminAuthorize;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
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

@RequestMapping("/posts")
@RequiredArgsConstructor
@RestController
@AdminAuthorize
public class PostController {

  private final PostCommentReadService commentReadService;
  private final PostReadService postReadService;
  private final PostCommentWriteService commentWriteService;
  private final PostWriteService postWriteService;
  private final PostInterestService postInterestService;
  private final PostCommentInterestService commentInterestService;
  private final PostReplyInterestService replyInterestService;
  private final PostReplyWriteService postReplyWriteService;
  private final PostReplyReadService postReplyReadService;


  @GetMapping("/comments")
  public List<PostCommentDto> findAllComments() {
    return commentReadService.findAll();
  }

  @GetMapping("/comments/{id}")
  public PostCommentDto findCommentById(@PathVariable Long id) {
    return commentReadService.findById(id);
  }

  @PostMapping("/comments")
  public PostCommentDto comment(@RequestBody @Valid WriteCommentRequest req) {
    return commentWriteService.write(req);
  }

  @PatchMapping("/comments")
  public PostCommentDto modifyComment(@RequestBody @Valid ModifyCommentRequest req) {
    return commentWriteService.modify(req);
  }

  @DeleteMapping("/comments/{id}")
  public void deleteCommentById(@PathVariable Long id) {
    commentWriteService.deleteById(id);
  }

  @PostMapping("/comments/replies")
  public PostReplyDto writeReply(@RequestBody @Valid WriteCommentRequest req) {
    return postReplyWriteService.write(req);
  }

  @GetMapping("/comment/replies")
  public List<PostReplyDto> findAllReply() {
    return postReplyReadService.findAll();
  }

  @GetMapping("/comment/{parentCommentId}/replies")
  public List<PostReplyDto> findAllRepliesByParentCommentId(@PathVariable Long parentCommentId) {
    return postReplyReadService.findAllByParentId(parentCommentId);
  }

  @GetMapping
  public List<PostDto> findAllPosts() {
    return postReadService.findAll();
  }

  @GetMapping("/{id}")
  public PostDto findPostById(@PathVariable Long id) {
    return postReadService.findById(id);
  }

  @PostMapping
  public PostDto writePost(@RequestBody @Valid PostWriteRequest req) {
    return postWriteService.write(req);
  }

  @DeleteMapping("/{id}")
  public void deletePostById(@PathVariable Long id) {
    postWriteService.deleteById(id);
  }

  @PostMapping("/interests")
  public PostInterestDto interestPost(
      @RequestBody @Valid WriteInterestRequest req
  ) {
    return postInterestService.write(req);
  }

  @GetMapping("/interests")
  public List<PostInterestDto> findAllPostInterests() {
    return postInterestService.findAll();
  }

  @GetMapping("/{postId}/interests")
  public List<PostInterestDto> findAllInterestByPostId(
      @PathVariable
      @Positive(message = "유효한 post id 유형이 아닙니다.")
      Long postId
  ) {
    return postInterestService.findAllByContentsId(postId);
  }

  @DeleteMapping("/interests/{interestId}")
  public void deletePostInterest(
      @PathVariable
      @Valid
      @Positive(message = "유효한 post id 유형이 아닙니다.")
      Long interestId
  ) {
    postInterestService.deleteById(interestId);
  }


  @PostMapping("/comments/interests")
  public PostCommentInterestDto writeCommentInterests(
      @RequestBody @Valid WriteInterestRequest req
  ) {
    return commentInterestService.write(req);
  }

  @GetMapping("/comments/interests")
  public List<PostCommentInterestDto> findAllCommentInterests() {
    return commentInterestService.findAll();
  }

  @GetMapping("/comments/{commentId}/interests")
  public List<PostCommentInterestDto> findAllCommentInterestsByCommentId(
      @PathVariable
      @Positive(message = "유효한 comment id 유형이 아닙니다.")
      Long commentId
  ) {
    return commentInterestService.findAllByInterestedCommentId(commentId);
  }

  @DeleteMapping("/comments/interests/{interestId}")
  public void deleteInterestByInterestId(
      @PathVariable
      @Valid
      @Positive(message = "유효한 comment id 유형이 아닙니다.")
      Long interestId
  ) {
    commentInterestService.deleteById(interestId);
  }

  @PostMapping("/comments/replies/interests")
  public PostReplyInterestDto writePostCommentReplyInterest(
      @RequestBody @Valid WriteInterestRequest req
  ) {
    return replyInterestService.write(req);
  }

  @GetMapping("/comments/replies/interests")
  public List<PostReplyInterestDto> findAllPostReplyInterests() {
    return replyInterestService.findAll();
  }

  @GetMapping("/comments/replies/{replyId}/interests")
  public List<PostReplyInterestDto> findPostReplyInterest(
      @PathVariable
      @Positive(message = "유효한 reply id 유형이 아닙니다.")
      Long replyId
  ) {
    return replyInterestService.findAllByInterestedReplyId(replyId);
  }

  @DeleteMapping("/comments/replies/interests/{interestId}")
  public void deletePostReplyInterest(
      @PathVariable
      @Valid
      @Positive(message = "유효한 interest id 유형이 아닙니다.")
      Long interestId
  ) {
    replyInterestService.deleteById(interestId);
  }
}
