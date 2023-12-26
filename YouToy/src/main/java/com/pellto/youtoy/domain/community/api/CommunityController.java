package com.pellto.youtoy.domain.community.api;

import com.pellto.youtoy.domain.community.application.CommentPostReadService;
import com.pellto.youtoy.domain.community.application.CommunityCommentInterestService;
import com.pellto.youtoy.domain.community.application.CommunityCommentReadService;
import com.pellto.youtoy.domain.community.application.CommunityCommentWriteService;
import com.pellto.youtoy.domain.community.application.CommunityPostWriteService;
import com.pellto.youtoy.domain.community.application.PostInterestService;
import com.pellto.youtoy.domain.community.application.PostReplyCommentReadService;
import com.pellto.youtoy.domain.community.application.PostReplyCommentWriteService;
import com.pellto.youtoy.domain.community.dto.CommunityCommentDto;
import com.pellto.youtoy.domain.community.dto.CommunityCommentInterestDto;
import com.pellto.youtoy.domain.community.dto.CommunityPostDto;
import com.pellto.youtoy.domain.community.dto.InterestRequest;
import com.pellto.youtoy.domain.community.dto.ModifyCommentRequest;
import com.pellto.youtoy.domain.community.dto.PostInterestDto;
import com.pellto.youtoy.domain.community.dto.PostReplyCommentDto;
import com.pellto.youtoy.domain.community.dto.WriteCommentRequest;
import com.pellto.youtoy.domain.community.dto.WritePostRequest;
import com.pellto.youtoy.domain.community.dto.WriteReplyRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/communities")
@RequiredArgsConstructor
@RestController
public class CommunityController {

  private final CommunityCommentReadService commentReadService;
  private final CommentPostReadService postReadService;
  private final CommunityCommentWriteService commentWriteService;
  private final CommunityPostWriteService postWriteService;
  private final PostInterestService postInterestService;
  private final CommunityCommentInterestService commentInterestService;
  private final PostReplyCommentWriteService postReplyCommentWriteService;
  private final PostReplyCommentReadService postReplyCommentReadService;


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

  @PostMapping("/comments/replies")
  public PostReplyCommentDto writeReply(@RequestBody @Valid WriteReplyRequest req) {
    return postReplyCommentWriteService.write(req);
  }

  @GetMapping("/comment/replies")
  public List<PostReplyCommentDto> findAllReply() {
    return postReplyCommentReadService.findAll();
  }

  @GetMapping("/comment/{parentCommentId}/replies")
  public List<PostReplyCommentDto> findAllByParentCommentId(@PathVariable Long parentCommentId) {
    return postReplyCommentReadService.findAllByParentId(parentCommentId);
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

  @PostMapping("/posts/{postId}/interesting")
  @Valid
  public PostInterestDto interestPost(
      @PathVariable
      @Positive(message = "유효한 post id 유형이 아닙니다.")
      Long postId,
      @RequestBody @Valid InterestRequest req
  ) {
    return postInterestService.interest(postId, req);
  }

  @GetMapping("/posts/interests")
  @Valid
  public List<PostInterestDto> findAllPostInterests(
      @RequestParam
      @Positive(message = "유효한 post id 유형이 아닙니다.")
      Long postId
  ) {
    return postInterestService.findAllByPostId(postId);
  }

  @GetMapping("/posts/{postId}/interests")
  @Valid
  public PostInterestDto findPostInterest(
      @PathVariable
      @Positive(message = "유효한 post id 유형이 아닙니다.")
      Long postId,
      @Pattern(
          regexp = "^[a-zA-Z0-9]{10}-[a-zA-Z0-9]{10}-[a-zA-Z0-9]{20}$",
          message = "유효하지 않은 uuid 패턴 입니다."
      )
      @RequestParam String userUUID
  ) {
    return postInterestService.findByPostIdAndUserUuid(postId, userUUID);
  }

  @DeleteMapping("/post/{postId}/interests")
  public void deletePostInterest(
      @PathVariable
      @Valid
      @Positive(message = "유효한 post id 유형이 아닙니다.")
      Long postId,
      @RequestParam
      @Valid @Pattern(
          regexp = "^[a-zA-Z0-9]{10}-[a-zA-Z0-9]{10}-[a-zA-Z0-9]{20}$",
          message = "유효하지 않은 uuid 패턴 입니다."
      )
      String userUUID
  ) {
    postInterestService.deleteInterestByPostIdAndUserUuid(postId, userUUID);
  }


  @PostMapping("/comments/{commentId}/interesting")
  public CommunityCommentInterestDto interestCommunityComment(
      @PathVariable
      @Valid
      @Positive(message = "유효한 comment id 유형이 아닙니다.")
      Long commentId,
      @RequestBody @Valid InterestRequest req
  ) {
    return commentInterestService.interest(commentId, req);
  }

  @GetMapping("/comments/interests")
  @Valid
  public List<CommunityCommentInterestDto> findAllCommunityCommentInterests() {
    return commentInterestService.findAll();
  }

  @GetMapping("/comments/{commentId}/interests")
  @Valid
  public CommunityCommentInterestDto findCommunityCommentInterest(
      @PathVariable
      @Positive(message = "유효한 comment id 유형이 아닙니다.")
      Long commentId,
      @Pattern(
          regexp = "^[a-zA-Z0-9]{10}-[a-zA-Z0-9]{10}-[a-zA-Z0-9]{20}$",
          message = "유효하지 않은 uuid 패턴 입니다."
      )
      @RequestParam String userUUID
  ) {
    return commentInterestService.findByCommentIdAndUserUuid(commentId, userUUID);
  }

  @DeleteMapping("/comments/{commentId}/interests")
  public void deleteCommunityCommentInterest(
      @PathVariable
      @Valid
      @Positive(message = "유효한 comment id 유형이 아닙니다.")
      Long commentId,
      @Valid
      @Pattern(
          regexp = "^[a-zA-Z0-9]{10}-[a-zA-Z0-9]{10}-[a-zA-Z0-9]{20}$",
          message = "유효하지 않은 uuid 패턴 입니다."
      )
      @RequestParam String userUUID
  ) {
    commentInterestService.deleteInterestByCommunityCommentIdAndUserUuid(commentId, userUUID);
  }
}
