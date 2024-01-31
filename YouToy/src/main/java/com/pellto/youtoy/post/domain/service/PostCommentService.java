package com.pellto.youtoy.post.domain.service;

import com.pellto.youtoy.global.event.comment.CommentWrittenEvent;
import com.pellto.youtoy.post.domain.port.in.CommentEventActions;
import com.pellto.youtoy.post.domain.port.out.LoadPostPort;
import com.pellto.youtoy.post.domain.port.out.SavePostPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostCommentService implements CommentEventActions {

  private final LoadPostPort loadPostPort;
  private final SavePostPort savePostPort;

  @Override
  public void increaseCommentCount(CommentWrittenEvent event) {
    var post = loadPostPort.load(event.getDto().contentsId());
    post.increaseCommentCount();
    savePostPort.update(post);
  }
}
