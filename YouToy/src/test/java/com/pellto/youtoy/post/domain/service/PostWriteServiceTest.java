package com.pellto.youtoy.post.domain.service;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.global.dto.post.PostDto;
import com.pellto.youtoy.post.domain.port.out.ChannelHandlePort;
import com.pellto.youtoy.post.domain.port.out.LoadPostPort;
import com.pellto.youtoy.post.domain.port.out.SavePostPort;
import com.pellto.youtoy.post.util.PostFixtureFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@Tag("service")
class PostWriteServiceTest {

  private static final String SERVICE_NAME = "PostWriteService";

  @InjectMocks
  private PostWriteService postWriteService;

  @Mock
  private LoadPostPort loadPostPort;
  @Mock
  private SavePostPort savePostPort;
  @Mock
  private ChannelHandlePort channelHandlePort;

  @DisplayName("[" + SERVICE_NAME + "/write] write 성공 테스트")
  @Test
  void writeSuccessTest() {
    var request = PostFixtureFactory.createWriteRequest();
    var beforeSaved = PostFixtureFactory.createBeforeSaved(request);
    var post = PostFixtureFactory.create(beforeSaved);

    given(channelHandlePort.existByChannelId(request.channelId())).willReturn(true);
    given(savePostPort.save(any())).willReturn(post);

    var writtenPost = postWriteService.write(request);

    Assertions.assertThat(writtenPost).isNotNull();
    Assertions.assertThat(writtenPost.id()).isNotNull();
    Assertions.assertThat(writtenPost.getClass()).isEqualTo(PostDto.class);
    Assertions.assertThat(writtenPost).usingRecursiveComparison().isEqualTo(post.toDto());
    then(channelHandlePort).should(times(1)).existByChannelId(any());
    then(savePostPort).should(times(1)).save(any());
  }

  @DisplayName("[" + SERVICE_NAME + "/write] write 실패 테스트")
  @Test
  void writeFailTest() {
    var request = PostFixtureFactory.createWriteRequest();

    given(channelHandlePort.existByChannelId(request.channelId())).willReturn(false);

    Assertions.assertThatThrownBy(() -> postWriteService.write(request)).isInstanceOf(
        IllegalArgumentException.class).hasMessage("채널 없음");

    then(channelHandlePort).should(times(1)).existByChannelId(any());
    then(savePostPort).should(times(0)).save(any());
  }

  @DisplayName("[" + SERVICE_NAME + "/changePostTitle] changePostTitle 성공 테스트")
  @Test
  void changePostTitleSuccessTest() {
    var alreadyExistPost = PostFixtureFactory.create();
    var changedTitle = "changedTitle";
    var post = PostFixtureFactory.createWithTitle(changedTitle);

    given(loadPostPort.load(alreadyExistPost.getId())).willReturn(alreadyExistPost);

    var changedPost = postWriteService.changePostTitle(alreadyExistPost.getId(), changedTitle);

    Assertions.assertThat(changedPost).usingRecursiveComparison().ignoringFields("updatedAt")
        .isEqualTo(post.toDto());
    Assertions.assertThat(changedPost.updatedAt()).isNotEqualTo(post.getUpdatedAt());
    then(loadPostPort).should(times(1)).load(alreadyExistPost.getId());
    then(savePostPort).should(times(1)).update(any());
  }

  @DisplayName("[" + SERVICE_NAME + "/changePostContent] changePostContent 성공 테스트")
  @Test
  void changePostContentSuccessTest() {
    var alreadyExistPost = PostFixtureFactory.create();
    var changedContent = "changedContent";
    var post = PostFixtureFactory.createWithContent(changedContent);

    given(loadPostPort.load(alreadyExistPost.getId())).willReturn(alreadyExistPost);

    var changedPost = postWriteService.changePostContent(alreadyExistPost.getId(), changedContent);

    Assertions.assertThat(changedPost).usingRecursiveComparison().ignoringFields("updatedAt")
        .isEqualTo(post.toDto());
    Assertions.assertThat(changedPost.updatedAt()).isNotEqualTo(post.getUpdatedAt());
    then(loadPostPort).should(times(1)).load(alreadyExistPost.getId());
    then(savePostPort).should(times(1)).update(any());
  }

  @DisplayName("[" + SERVICE_NAME + "/changePost] changePost 성공 테스트")
  @Test
  void changePostSuccessTest() {
    var alreadyExistPost = PostFixtureFactory.create();
    var newPostContent = PostFixtureFactory.createNewPostContent("newTitle", "newContent");
    var post = PostFixtureFactory.create(newPostContent);

    given(loadPostPort.load(alreadyExistPost.getId())).willReturn(alreadyExistPost);

    var changedPost = postWriteService.changePost(alreadyExistPost.getId(), newPostContent);

    Assertions.assertThat(changedPost).usingRecursiveComparison().ignoringFields("updatedAt")
        .isEqualTo(post.toDto());
    Assertions.assertThat(changedPost.updatedAt()).isNotEqualTo(post.getUpdatedAt());
    then(loadPostPort).should(times(1)).load(alreadyExistPost.getId());
    then(savePostPort).should(times(1)).update(any());
  }
}