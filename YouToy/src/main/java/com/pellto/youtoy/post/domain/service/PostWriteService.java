package com.pellto.youtoy.post.domain.service;

import com.pellto.youtoy.global.dto.post.PostDto;
import com.pellto.youtoy.global.dto.post.request.WritePostRequest;
import com.pellto.youtoy.post.domain.model.Post;
import com.pellto.youtoy.post.domain.model.PostContent;
import com.pellto.youtoy.post.domain.port.in.ChangePostContentUsecase;
import com.pellto.youtoy.post.domain.port.in.RemovePostUsecase;
import com.pellto.youtoy.post.domain.port.in.WritePostUsecase;
import com.pellto.youtoy.post.domain.port.out.ChannelHandlePort;
import com.pellto.youtoy.post.domain.port.out.LoadPostPort;
import com.pellto.youtoy.post.domain.port.out.PostEventPort;
import com.pellto.youtoy.post.domain.port.out.SavePostPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostWriteService implements WritePostUsecase, ChangePostContentUsecase,
    RemovePostUsecase {

  private final LoadPostPort loadPostPort;
  private final SavePostPort savePostPort;
  private final ChannelHandlePort channelHandlePort;
  private final PostEventPort postEventPort;

  @Override
  public PostDto changePostTitle(Long id, String title) {
    var post = loadPostPort.load(id);
    post.changeTitle(title);
    savePostPort.update(post);
    return post.toDto();
  }

  @Override
  public PostDto changePostContent(Long id, String content) {
    var post = loadPostPort.load(id);
    post.changeContent(content);
    savePostPort.update(post);
    return post.toDto();
  }

  @Override
  public PostDto changePost(Long id, PostContent postContent) {
    var post = loadPostPort.load(id);
    post.changePost(postContent);
    savePostPort.update(post);
    return post.toDto();
  }

  @Override
  @Transactional
  public void remove(Long postId) {
    savePostPort.deleteById(postId);
    postEventPort.postRemovedEvent(postId);
  }

  @Override
  public PostDto write(WritePostRequest request) {
    if (!channelHandlePort.existByChannelId(request.channelId())) {
      throw new IllegalArgumentException("채널 없음");
    }
    var postContent = PostContent.builder()
        .content(request.content())
        .title(request.title())
        .build();

    var post = Post.builder()
        .channelId(request.channelId())
        .postContent(postContent)
        .build();

    post = savePostPort.save(post);
    return post.toDto();
  }
}
