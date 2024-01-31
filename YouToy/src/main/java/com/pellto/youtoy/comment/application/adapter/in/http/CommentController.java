package com.pellto.youtoy.comment.application.adapter.in.http;

import com.pellto.youtoy.comment.domain.port.in.ChangeCommentContentUsecase;
import com.pellto.youtoy.comment.domain.port.in.WriteCommentUsecase;
import com.pellto.youtoy.global.dto.comment.CommentDto;
import com.pellto.youtoy.global.dto.comment.request.ChangeCommentRequest;
import com.pellto.youtoy.global.dto.comment.request.WriteCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

  private final ChangeCommentContentUsecase changeCommentContentUsecase;
  private final WriteCommentUsecase writeCommentUsecase;

  @PostMapping
  public CommentDto write(@RequestBody WriteCommentRequest request) {
    return writeCommentUsecase.write(request);
  }

  @PatchMapping
  public CommentDto changeContent(@RequestBody ChangeCommentRequest request) {
    return changeCommentContentUsecase.change(request);
  }
}
