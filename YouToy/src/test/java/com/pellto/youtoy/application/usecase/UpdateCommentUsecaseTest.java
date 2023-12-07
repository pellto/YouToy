package com.pellto.youtoy.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.times;

import com.pellto.youtoy.domain.channel.service.ChannelReadService;
import com.pellto.youtoy.domain.comment.service.CommentReadService;
import com.pellto.youtoy.domain.comment.service.CommentWriteService;
import com.pellto.youtoy.domain.comment.service.MentionWriteService;
import com.pellto.youtoy.util.channel.ChannelFixtureFactory;
import com.pellto.youtoy.util.comment.CommentDtoFixtureFactory;
import com.pellto.youtoy.util.comment.UpdateCommentCommandFixtureFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@Tag("usecase")
@ExtendWith(MockitoExtension.class)
@DisplayName("[UpdateCommentUsecase Test]")
public class UpdateCommentUsecaseTest {

  private static final String ALREADY_CHANNEL_HANDLE = "@already-mentioned";
  private static final String ALREADY_MENTIONED_CONTENT = ALREADY_CHANNEL_HANDLE + " content";
  private static final String MENTIONED_HANDLE = "@mentioned";
  private static final String MENTIONED_CONTENT = MENTIONED_HANDLE + " content";
  @InjectMocks
  private UpdateCommentUsecase updateCommentUsecase;
  @Mock
  private ChannelReadService channelReadService;
  @Mock
  private CommentWriteService commentWriteService;
  @Mock
  private CommentReadService commentReadService;
  @Mock
  private MentionWriteService mentionWriteService;

  @DisplayName("With Mention")
  @Nested
  public class WithMentionTest {

    @DisplayName("[execute: success] 기존 댓글에 멘션이 있을 경우 댓글 수정 성공 테스트")
    @Test
    public void executeAlreadyMentionTest() {
      var cmd = UpdateCommentCommandFixtureFactory.create(MENTIONED_CONTENT);
      var commentDto = CommentDtoFixtureFactory.create(ALREADY_MENTIONED_CONTENT);
      var updatedCommentDto = CommentDtoFixtureFactory.create(cmd);
      var channelDto = ChannelFixtureFactory.toDto(
          ChannelFixtureFactory.create(MENTIONED_HANDLE)
      );

      given(commentReadService.get(cmd.id())).willReturn(commentDto);
      given(commentWriteService.update(cmd)).willReturn(updatedCommentDto);
      given(channelReadService.getByHandle(MENTIONED_HANDLE)).willReturn(channelDto);

      var newCommentDto = updateCommentUsecase.execute(cmd);

      assertEquals(updatedCommentDto, newCommentDto);
      then(commentReadService).should(times(1)).get(cmd.id());
      then(mentionWriteService).should(times(1)).deleteByCommentId(cmd.id());
      then(commentWriteService).should(times(1)).update(cmd);
      then(channelReadService).should(times(1)).getByHandle(MENTIONED_HANDLE);
      then(mentionWriteService).should(times(1)).create(any());
    }

    @DisplayName("[execute: success] 다중 멘션 댓글 수정 성공 테스트")
    @Test
    public void executeMultiMentionTest() {
      var cmd = UpdateCommentCommandFixtureFactory.create(
          ALREADY_CHANNEL_HANDLE + " " + MENTIONED_CONTENT
      );
      var commentDto = CommentDtoFixtureFactory.create();
      var updatedCommentDto = CommentDtoFixtureFactory.create(cmd);
      var channelDto = ChannelFixtureFactory.toDto(
          ChannelFixtureFactory.create(MENTIONED_HANDLE)
      );
      var channelDto2 = ChannelFixtureFactory.toDto(
          ChannelFixtureFactory.create(ALREADY_CHANNEL_HANDLE)
      );

      given(commentReadService.get(cmd.id())).willReturn(commentDto);
      given(commentWriteService.update(cmd)).willReturn(updatedCommentDto);
      given(channelReadService.getByHandle(MENTIONED_HANDLE)).willReturn(channelDto);
      given(channelReadService.getByHandle(ALREADY_CHANNEL_HANDLE)).willReturn(channelDto2);

      var newCommentDto = updateCommentUsecase.execute(cmd);

      assertEquals(updatedCommentDto, newCommentDto);
      then(commentReadService).should(times(1)).get(cmd.id());
      then(mentionWriteService).should(times(0)).deleteByCommentId(cmd.id());
      then(commentWriteService).should(times(1)).update(cmd);
      then(channelReadService).should(times(1)).getByHandle(MENTIONED_HANDLE);
      then(channelReadService).should(times(1)).getByHandle(ALREADY_CHANNEL_HANDLE);
      then(mentionWriteService).should(times(2)).create(any());
    }

    @DisplayName("[execute: success] 댓글 수정 성공 테스트")
    @Test
    public void executeTest() {
      var cmd = UpdateCommentCommandFixtureFactory.create(MENTIONED_CONTENT);
      var commentDto = CommentDtoFixtureFactory.create();
      var updatedCommentDto = CommentDtoFixtureFactory.create(cmd);
      var channelDto = ChannelFixtureFactory.toDto(
          ChannelFixtureFactory.create(MENTIONED_HANDLE)
      );

      given(commentReadService.get(cmd.id())).willReturn(commentDto);
      given(commentWriteService.update(cmd)).willReturn(updatedCommentDto);
      given(channelReadService.getByHandle(MENTIONED_HANDLE)).willReturn(channelDto);

      var newCommentDto = updateCommentUsecase.execute(cmd);

      assertEquals(updatedCommentDto, newCommentDto);
      then(commentReadService).should(times(1)).get(cmd.id());
      then(mentionWriteService).should(times(0)).deleteByCommentId(any());
      then(commentWriteService).should(times(1)).update(cmd);
      then(channelReadService).should(times(1)).getByHandle(MENTIONED_HANDLE);
      then(mentionWriteService).should(times(1)).create(any());
    }
  }

  @DisplayName("Without Mention")
  @Nested
  public class WithoutMentionTest {

    @DisplayName("[execute: success] 기존 댓글에 멘션이 있을 경우 댓글 수정 성공 테스트")
    @Test
    public void executeAlreadyMentionTest() {
      var cmd = UpdateCommentCommandFixtureFactory.create();
      var commentDto = CommentDtoFixtureFactory.create(ALREADY_MENTIONED_CONTENT);
      var updatedCommentDto = CommentDtoFixtureFactory.create(cmd);

      given(commentReadService.get(cmd.id())).willReturn(commentDto);
      given(commentWriteService.update(cmd)).willReturn(updatedCommentDto);

      var newCommentDto = updateCommentUsecase.execute(cmd);

      assertEquals(updatedCommentDto, newCommentDto);
      then(commentReadService).should(times(1)).get(cmd.id());
      then(mentionWriteService).should(times(1)).deleteByCommentId(cmd.id());
      then(commentWriteService).should(times(1)).update(cmd);
      then(channelReadService).should(times(0)).getByHandle(any());
      then(mentionWriteService).should(times(0)).create(any());
    }

    @DisplayName("[execute: success] 댓글 수정 성공 테스트")
    @Test
    public void executeTest() {
      var cmd = UpdateCommentCommandFixtureFactory.create();
      var commentDto = CommentDtoFixtureFactory.create();
      var updatedCommentDto = CommentDtoFixtureFactory.create(cmd);

      given(commentReadService.get(cmd.id())).willReturn(commentDto);
      given(commentWriteService.update(cmd)).willReturn(updatedCommentDto);

      var newCommentDto = updateCommentUsecase.execute(cmd);

      assertEquals(updatedCommentDto, newCommentDto);
      then(commentReadService).should(times(1)).get(cmd.id());
      then(mentionWriteService).should(times(0)).deleteByCommentId(any());
      then(commentWriteService).should(times(1)).update(cmd);
      then(channelReadService).should(times(0)).getByHandle(any());
      then(mentionWriteService).should(times(0)).create(any());
    }
  }
}
