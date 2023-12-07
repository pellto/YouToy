package com.pellto.youtoy.application.controller;

import com.pellto.youtoy.application.usecase.CreateCommentUsecase;
import com.pellto.youtoy.application.usecase.GetCommentsUsecase;
import com.pellto.youtoy.application.usecase.UpdateCommentUsecase;
import com.pellto.youtoy.domain.comment.dto.CommentDto;
import com.pellto.youtoy.domain.comment.dto.CreateCommentCommand;
import com.pellto.youtoy.domain.comment.dto.UpdateCommentCommand;
import com.pellto.youtoy.domain.comment.service.CommentReadService;
import com.pellto.youtoy.domain.comment.service.CommentWriteService;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentController {

  private final CreateCommentUsecase createCommentUsecase;
  private final UpdateCommentUsecase updateCommentUsecase;
  private final GetCommentsUsecase getCommentsUsecase;
  private final CommentWriteService commentWriteService;
  private final CommentReadService commentReadService;


  @PostMapping
  public CommentDto create(@RequestBody CreateCommentCommand cmd) {
    return createCommentUsecase.execute(cmd);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    commentWriteService.delete(id);
  }

  @GetMapping("/{id}")
  public CommentDto get(@PathVariable Long id) {
    return commentReadService.get(id);
  }

  @GetMapping("/replies/{id}")
  public List<CommentDto> getReplies(@PathVariable Long id) {
    return commentReadService.getReplies(id);
  }

  @GetMapping("/short/{shortId}")
  public List<CommentDto> getShortComments(@PathVariable Long shortId) {
    return getCommentsUsecase.execute(shortId, false);
  }

  @GetMapping("/video/{videoId}")
  public List<CommentDto> getVideoComments(@PathVariable Long videoId) {
    return getCommentsUsecase.execute(videoId, true);
  }

  @PatchMapping
  public CommentDto update(@RequestBody UpdateCommentCommand cmd) {
    return updateCommentUsecase.execute(cmd);
  }
}
