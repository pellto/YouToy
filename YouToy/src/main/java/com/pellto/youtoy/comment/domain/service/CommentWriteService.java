package com.pellto.youtoy.comment.domain.service;

import com.pellto.youtoy.comment.domain.model.Comment;
import com.pellto.youtoy.comment.domain.model.CommenterInfo;
import com.pellto.youtoy.comment.domain.port.in.ChangeCommentContentUsecase;
import com.pellto.youtoy.comment.domain.port.in.WriteCommentUsecase;
import com.pellto.youtoy.comment.domain.port.out.event.CommentEventPort;
import com.pellto.youtoy.comment.domain.port.out.http.ChannelHandlePort;
import com.pellto.youtoy.comment.domain.port.out.http.ContentsExistHandlePort;
import com.pellto.youtoy.comment.domain.port.out.persistence.LoadCommentPort;
import com.pellto.youtoy.comment.domain.port.out.persistence.SaveCommentPort;
import com.pellto.youtoy.global.dto.comment.CommentDto;
import com.pellto.youtoy.global.dto.comment.request.ChangeCommentRequest;
import com.pellto.youtoy.global.dto.comment.request.WriteCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CommentWriteService implements WriteCommentUsecase, ChangeCommentContentUsecase {

  private final LoadCommentPort loadCommentPort;
  private final SaveCommentPort saveCommentPort;
  private final CommentEventPort commentEventPort;
  private final ContentsExistHandlePort contentsExistHandlePort;
  private final ChannelHandlePort channelHandlePort;

  @Override
  public CommentDto change(ChangeCommentRequest request) {
    var comment = loadCommentPort.load(request.commentId());
    var before = comment.toDto();
    comment.changeContent(request.content());
    saveCommentPort.update(comment);
    var after = comment.toDto();
    commentEventPort.commentChangedEvent(before, after);
    return after;
  }

  @Override
  public CommentDto write(WriteCommentRequest request) {
    // TODO: check is commenter exist
    if (!contentsExistHandlePort.isExistContents(request.contentsType(), request.contentsId())) {
      throw new IllegalArgumentException("해당 컨텐츠가 존재하지 않습니다.");
    }
    var commenterChannelInfo = channelHandlePort.getCommenterChannelInfo(request.commenterId());
    var commenterInfo = CommenterInfo.builder()
        .commenterId(commenterChannelInfo.commenterId())
        .commenterHandle(commenterChannelInfo.commenterHandle())
        .displayName(commenterChannelInfo.displayName())
        .profilePath(commenterChannelInfo.profilePath())
        .build();
    var comment = Comment.builder()
        .commenterInfo(commenterInfo)
        .contentsId(request.contentsId())
        .commentContentsType(request.contentsType())
        .content(request.content())
        .build();

    comment = saveCommentPort.save(comment);
    var dto = comment.toDto();
    commentEventPort.commentWrittenEvent(dto);
    return dto;
  }

  @Override
  public void remove(Long id) {
    saveCommentPort.deleteById(id);
    commentEventPort.commentRemovedEvent(id);
  }
}
