package com.pellto.youtoy.comment.domain.service;

import com.pellto.youtoy.comment.domain.model.CommenterInfo;
import com.pellto.youtoy.comment.domain.model.Reply;
import com.pellto.youtoy.comment.domain.port.in.WriteReplyUsecase;
import com.pellto.youtoy.comment.domain.port.out.http.ChannelHandlePort;
import com.pellto.youtoy.comment.domain.port.out.persistence.SaveReplyPort;
import com.pellto.youtoy.global.dto.comment.ReplyDto;
import com.pellto.youtoy.global.dto.comment.request.WriteReplyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ReplyWriteService implements WriteReplyUsecase {

  private final CommentReadService commentReadService;
  private final SaveReplyPort saveReplyPort;
  private final ChannelHandlePort channelHandlePort;


  @Override
  public ReplyDto writeReply(WriteReplyRequest request) {
    if (!commentReadService.isExistCommentById(request.parentCommentId())) {
      throw new IllegalArgumentException("부모 댓글 없음");
    }
    var replierInfoResponse = channelHandlePort.getCommenterChannelInfo(request.replierId());
    var replierInfo = CommenterInfo.builder()
        .commenterId(replierInfoResponse.commenterId())
        .commenterHandle(replierInfoResponse.commenterHandle())
        .displayName(replierInfoResponse.displayName())
        .profilePath(replierInfoResponse.profilePath())
        .build();

    var reply = Reply.builder()
        .parentCommentId(request.parentCommentId())
        .replierInfo(replierInfo)
        .content(request.content())
        .build();

    reply = saveReplyPort.save(reply);
    return reply.toDto();
  }

  @Override
  public void removeReply(Long replyId) {
    saveReplyPort.deleteById(replyId);
  }
}
