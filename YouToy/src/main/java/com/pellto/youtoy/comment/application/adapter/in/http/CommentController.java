package com.pellto.youtoy.comment.application.adapter.in.http;

import com.pellto.youtoy.comment.domain.port.in.ChangeCommentContentUsecase;
import com.pellto.youtoy.comment.domain.port.in.ReadCommentUsecase;
import com.pellto.youtoy.comment.domain.port.in.WriteCommentUsecase;
import com.pellto.youtoy.global.dto.comment.CommentDto;
import com.pellto.youtoy.global.dto.comment.request.ChangeCommentRequest;
import com.pellto.youtoy.global.dto.comment.request.WriteCommentRequest;
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

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

  private final ChangeCommentContentUsecase changeCommentContentUsecase;
  private final WriteCommentUsecase writeCommentUsecase;
  private final ReadCommentUsecase readCommentUsecase;

  @PostMapping
  public CommentDto write(@RequestBody WriteCommentRequest request) {
    return writeCommentUsecase.write(request);
  }

  @PatchMapping
  public CommentDto changeContent(@RequestBody ChangeCommentRequest request) {
    return changeCommentContentUsecase.change(request);
  }

  @GetMapping("/post/{contentsId}")
  public List<CommentDto> readPostComments(@PathVariable Long contentsId) {
    return readCommentUsecase.readAllByContentsTypeAndContentsId("POST", contentsId);
  }

  @GetMapping("/exist/{commentId}")
  public boolean isExistComment(@PathVariable Long commentId) {
    return readCommentUsecase.isExistCommentById(commentId);
  }

  @DeleteMapping("/{commentId}")
  public void removeById(@PathVariable Long commentId) {
    writeCommentUsecase.remove(commentId);
  }
}
