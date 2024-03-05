package com.pellto.youtoy.comment.domain.service;

import com.pellto.youtoy.comment.domain.model.CommenterInfo;
import com.pellto.youtoy.comment.domain.model.Reply;
import com.pellto.youtoy.comment.domain.port.in.WriteReplyUsecase;
import com.pellto.youtoy.comment.domain.port.out.event.ReplyEventPort;
import com.pellto.youtoy.comment.domain.port.out.http.ChannelHandlePort;
import com.pellto.youtoy.comment.domain.port.out.persistence.LoadCommentPort;
import com.pellto.youtoy.comment.domain.port.out.persistence.LoadReplyPort;
import com.pellto.youtoy.comment.domain.port.out.persistence.SaveReplyPort;
import com.pellto.youtoy.global.dto.comment.ReplyDto;
import com.pellto.youtoy.global.dto.comment.request.WriteReplyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ReplyWriteService implements WriteReplyUsecase {

  private final LoadCommentPort loadCommentPort;
  private final LoadReplyPort loadReplyPort;
  private final SaveReplyPort saveReplyPort;
  private final ReplyEventPort replyEventPort;
  private final ChannelHandlePort channelHandlePort;


  @Override
  public ReplyDto writeReply(WriteReplyRequest request) {
    if (!loadCommentPort.isExistById(request.parentCommentId())) {
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
    var replyDto = reply.toDto();
    replyEventPort.replyWrittenEvent(replyDto);
    return replyDto;
  }

  @Override
  public void removeReply(Long replyId) {
    var reply = loadReplyPort.load(replyId);
    saveReplyPort.deleteById(replyId);
    replyEventPort.replyRemovedEvent(replyId, reply.getParentCommentId());
  }
}
