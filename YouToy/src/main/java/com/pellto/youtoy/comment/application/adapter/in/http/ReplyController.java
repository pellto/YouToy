package com.pellto.youtoy.comment.application.adapter.in.http;

import com.pellto.youtoy.comment.domain.port.in.WriteReplyUsecase;
import com.pellto.youtoy.global.dto.comment.ReplyDto;
import com.pellto.youtoy.global.dto.comment.request.WriteReplyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/replies")
@RequiredArgsConstructor
public class ReplyController {

  private final WriteReplyUsecase writeReplyUsecase;

  @PostMapping
  public ReplyDto writeReply(@RequestBody WriteReplyRequest request) {
    return writeReplyUsecase.writeReply(request);
  }
}
