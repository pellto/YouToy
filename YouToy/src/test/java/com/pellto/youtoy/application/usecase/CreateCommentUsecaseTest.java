package com.pellto.youtoy.application.usecase;

import com.pellto.youtoy.domain.channel.service.ChannelReadService;
import com.pellto.youtoy.domain.comment.service.CommentWriteService;
import com.pellto.youtoy.domain.comment.service.MentionWriteService;
import com.pellto.youtoy.domain.view.service.ShortReadService;
import com.pellto.youtoy.domain.view.service.VideoReadService;
import com.pellto.youtoy.util.channel.ChannelFixtureFactory;
import com.pellto.youtoy.util.comment.CommentDtoFixtureFactory;
import com.pellto.youtoy.util.comment.CreateCommentCommandFixtureFactory;
import com.pellto.youtoy.util.comment.CreateMentionCommandFixtureFactory;
import com.pellto.youtoy.util.error.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.*;

@Tag("usecase")
@ExtendWith(MockitoExtension.class)
@DisplayName("[CreateCommentUsecase Test]")
public class CreateCommentUsecaseTest {
    @InjectMocks
    private CreateCommentUsecase createCommentUsecase;
    @Mock
    private ChannelReadService channelReadService;
    @Mock
    private VideoReadService videoReadService;
    @Mock
    private ShortReadService shortReadService;
    @Mock
    private CommentWriteService commentWriteService;
    @Mock
    private MentionWriteService mentionWriteService;

    @Nested
    @DisplayName("Without mention")
    public class WithoutMentionExecuteTest {
        @DisplayName("[execute: success] 비디오에 댓글 생성 실행 성공 테스트")
        @Test
        public void executeVideoCommentTest() {
            var cmd = CreateCommentCommandFixtureFactory.create();
            var commentDto = CommentDtoFixtureFactory.create();

            given(videoReadService.existVideo(any())).willReturn(true);
            given(commentWriteService.create(any())).willReturn(commentDto);

            var createdCommentDto = createCommentUsecase.execute(cmd);

            assertEquals(cmd.userId(), createdCommentDto.userId());
            assertEquals(cmd.content(), createdCommentDto.content());
            assertEquals(0L, createdCommentDto.replyCnt());
            then(videoReadService).should(times(1)).existVideo(any());
            then(shortReadService).should(times(0)).existShort(any());
            then(commentWriteService).should(times(1)).create(any());
            then(channelReadService).should(times(0)).getByHandle(any());
            then(mentionWriteService).should(times(0)).create(any());
        }

        @DisplayName("[execute: success] 쇼츠에 댓글 생성 성공 테스트")
        @Test
        public void executeShortCommentTest() {
            var cmd = CreateCommentCommandFixtureFactory.create(false);
            var commentDto = CommentDtoFixtureFactory.create();

            given(shortReadService.existShort(any())).willReturn(true);
            given(commentWriteService.create(any())).willReturn(commentDto);

            var createdCommentDto = createCommentUsecase.execute(cmd);

            assertEquals(cmd.userId(), createdCommentDto.userId());
            assertEquals(cmd.content(), createdCommentDto.content());
            assertEquals(0L, createdCommentDto.replyCnt());
            then(videoReadService).should(times(0)).existVideo(any());
            then(shortReadService).should(times(1)).existShort(any());
            then(commentWriteService).should(times(1)).create(any());
            then(channelReadService).should(times(0)).getByHandle(any());
            then(mentionWriteService).should(times(0)).create(any());
        }

        @DisplayName("[execute: not exist video] 존재하지 않는 비디오 댓글 실패 테스트")
        @Test
        public void executeNotExistVideoTest() {
            var cmd = CreateCommentCommandFixtureFactory.create();

            given(videoReadService.existVideo(any())).willReturn(false);

            try {
                createCommentUsecase.execute(cmd);
            } catch (Exception e) {
                assertEquals(ErrorCode.NOT_EXIST_VIDEO.getMessage(), e.getMessage());
                then(videoReadService).should(times(1)).existVideo(any());
                then(shortReadService).should(times(0)).existShort(any());
                then(commentWriteService).should(times(0)).create(any());
                then(channelReadService).should(times(0)).getByHandle(any());
                then(mentionWriteService).should(times(0)).create(any());
            }
        }

        @DisplayName("[execute: not exist short] 존재하지 않는 쇼츠 댓글 실패 테스트")
        @Test
        public void executeNotExistShortTest() {
            var cmd = CreateCommentCommandFixtureFactory.create(false);

            given(shortReadService.existShort(any())).willReturn(false);

            try {
                createCommentUsecase.execute(cmd);
            } catch (Exception e) {
                assertEquals(ErrorCode.NOT_EXIST_SHORT.getMessage(), e.getMessage());
                then(videoReadService).should(times(0)).existVideo(any());
                then(shortReadService).should(times(1)).existShort(any());
                then(commentWriteService).should(times(0)).create(any());
                then(channelReadService).should(times(0)).getByHandle(any());
                then(mentionWriteService).should(times(0)).create(any());
            }
        }
    }

    @Nested
    @DisplayName("With mention")
    public class WithMentionExecuteTest {
        private static final String MENTIONED_HANDLE = "@mention-test";
        private static final String MENTIONED_HANDLE_2 = "@mention-test2";
        private static final String CONTENT_WITH_MENTION = String.format("%s content", MENTIONED_HANDLE);
        private static final String MULTI_MENTION_CONTENT = String.format(
                "%s %s content", MENTIONED_HANDLE, MENTIONED_HANDLE_2
        );

        @DisplayName("[execute: success] 비디오에 댓글 생성 실행 성공 테스트")
        @Test
        public void executeVideoCommentTest() {
            var cmd = CreateCommentCommandFixtureFactory.create(CONTENT_WITH_MENTION);
            var commentDto = CommentDtoFixtureFactory.create(cmd);
            var channelDto = ChannelFixtureFactory.toDto(ChannelFixtureFactory.create());

            given(videoReadService.existVideo(any())).willReturn(true);
            given(commentWriteService.create(any())).willReturn(commentDto);
            given(channelReadService.getByHandle(MENTIONED_HANDLE)).willReturn(channelDto);

            var createdCommentDto = createCommentUsecase.execute(cmd);

            assertEquals(cmd.userId(), createdCommentDto.userId());
            assertEquals(cmd.content(), createdCommentDto.content());
            assertEquals(0L, createdCommentDto.replyCnt());
            then(videoReadService).should(times(1)).existVideo(any());
            then(shortReadService).should(times(0)).existShort(any());
            then(commentWriteService).should(times(1)).create(any());
            then(channelReadService).should(times(1)).getByHandle(any());
            then(mentionWriteService).should(times(1)).create(any());
        }

        @DisplayName("[execute: success] 쇼츠에 댓글 생성 성공 테스트")
        @Test
        public void executeShortCommentTest() {
            var cmd = CreateCommentCommandFixtureFactory.create(false, CONTENT_WITH_MENTION);
            var commentDto = CommentDtoFixtureFactory.create(cmd);
            var channelDto = ChannelFixtureFactory.toDto(ChannelFixtureFactory.create());

            given(shortReadService.existShort(any())).willReturn(true);
            given(commentWriteService.create(any())).willReturn(commentDto);
            given(channelReadService.getByHandle(MENTIONED_HANDLE)).willReturn(channelDto);

            var createdCommentDto = createCommentUsecase.execute(cmd);

            assertEquals(cmd.userId(), createdCommentDto.userId());
            assertEquals(cmd.content(), createdCommentDto.content());
            assertEquals(0L, createdCommentDto.replyCnt());
            then(videoReadService).should(times(0)).existVideo(any());
            then(shortReadService).should(times(1)).existShort(any());
            then(commentWriteService).should(times(1)).create(any());
            then(channelReadService).should(times(1)).getByHandle(any());
            then(mentionWriteService).should(times(1)).create(any());
        }

        @DisplayName("[execute: not exist video] 존재하지 않는 비디오 댓글 실패 테스트")
        @Test
        public void executeNotExistVideoTest() {
            var cmd = CreateCommentCommandFixtureFactory.create(CONTENT_WITH_MENTION);

            given(videoReadService.existVideo(any())).willReturn(false);

            try {
                createCommentUsecase.execute(cmd);
            } catch (Exception e) {
                assertEquals(ErrorCode.NOT_EXIST_VIDEO.getMessage(), e.getMessage());
                then(videoReadService).should(times(1)).existVideo(any());
                then(shortReadService).should(times(0)).existShort(any());
                then(commentWriteService).should(times(0)).create(any());
                then(channelReadService).should(times(0)).getByHandle(any());
                then(mentionWriteService).should(times(0)).create(any());
            }
        }

        @DisplayName("[execute: not exist short] 존재하지 않는 쇼츠 댓글 실패 테스트")
        @Test
        public void executeNotExistShortTest() {
            var cmd = CreateCommentCommandFixtureFactory.create(false, CONTENT_WITH_MENTION);

            given(shortReadService.existShort(any())).willReturn(false);

            try {
                createCommentUsecase.execute(cmd);
            } catch (Exception e) {
                assertEquals(ErrorCode.NOT_EXIST_SHORT.getMessage(), e.getMessage());
                then(videoReadService).should(times(0)).existVideo(any());
                then(shortReadService).should(times(1)).existShort(any());
                then(commentWriteService).should(times(0)).create(any());
                then(channelReadService).should(times(0)).getByHandle(any());
                then(mentionWriteService).should(times(0)).create(any());
            }
        }

        @DisplayName("[execute: success: multi mention] 비디오에 다중 멘션 댓글 생성 실행 성공 테스트")
        @Test
        public void executeVideoCommentMultiMentionTest() {
            var channelId = 2L;
            var cmd = CreateCommentCommandFixtureFactory.create(MULTI_MENTION_CONTENT);
            var commentDto = CommentDtoFixtureFactory.create(cmd);
            var channelDto = ChannelFixtureFactory.toDto(ChannelFixtureFactory.create());
            var channelDto2 = ChannelFixtureFactory.toDto(ChannelFixtureFactory.create(channelId));
            var createMentionCommand = CreateMentionCommandFixtureFactory
                    .create(commentDto.id(), channelDto.id(), commentDto.createdAt());
            var createMentionCommand2 = CreateMentionCommandFixtureFactory
                    .create(commentDto.id(), channelDto2.id(), commentDto.createdAt());

            given(videoReadService.existVideo(any())).willReturn(true);
            given(commentWriteService.create(any())).willReturn(commentDto);
            given(channelReadService.getByHandle(MENTIONED_HANDLE)).willReturn(channelDto);
            given(channelReadService.getByHandle(MENTIONED_HANDLE_2)).willReturn(channelDto2);

            var createdCommentDto = createCommentUsecase.execute(cmd);

            assertEquals(cmd.userId(), createdCommentDto.userId());
            assertEquals(cmd.content(), createdCommentDto.content());
            assertEquals(0L, createdCommentDto.replyCnt());
            then(videoReadService).should(times(1)).existVideo(any());
            then(shortReadService).should(times(0)).existShort(any());
            then(commentWriteService).should(times(1)).create(any());
            then(channelReadService).should(times(1)).getByHandle(MENTIONED_HANDLE);
            then(channelReadService).should(times(1)).getByHandle(MENTIONED_HANDLE_2);
            then(mentionWriteService).should(times(1)).create(createMentionCommand);
            then(mentionWriteService).should(times(1)).create(createMentionCommand2);
        }

        @DisplayName("[execute: success] 쇼츠에 다중 멘션 댓글 생성 성공 테스트")
        @Test
        public void executeShortCommentMultiMentionTest() {
            var channelId = 2L;
            var cmd = CreateCommentCommandFixtureFactory.create(false, MULTI_MENTION_CONTENT);
            var commentDto = CommentDtoFixtureFactory.create(cmd);
            var channelDto = ChannelFixtureFactory.toDto(ChannelFixtureFactory.create());
            var channelDto2 = ChannelFixtureFactory.toDto(ChannelFixtureFactory.create(channelId));
            var createMentionCommand = CreateMentionCommandFixtureFactory
                    .create(commentDto.id(), channelDto.id(), commentDto.createdAt());
            var createMentionCommand2 = CreateMentionCommandFixtureFactory
                    .create(commentDto.id(), channelDto2.id(), commentDto.createdAt());

            given(shortReadService.existShort(any())).willReturn(true);
            given(commentWriteService.create(any())).willReturn(commentDto);
            given(channelReadService.getByHandle(MENTIONED_HANDLE)).willReturn(channelDto);
            given(channelReadService.getByHandle(MENTIONED_HANDLE_2)).willReturn(channelDto2);

            var createdCommentDto = createCommentUsecase.execute(cmd);

            assertEquals(cmd.userId(), createdCommentDto.userId());
            assertEquals(cmd.content(), createdCommentDto.content());
            assertEquals(0L, createdCommentDto.replyCnt());
            then(videoReadService).should(times(0)).existVideo(any());
            then(shortReadService).should(times(1)).existShort(any());
            then(commentWriteService).should(times(1)).create(any());
            then(channelReadService).should(times(1)).getByHandle(MENTIONED_HANDLE);
            then(channelReadService).should(times(1)).getByHandle(MENTIONED_HANDLE_2);
            then(mentionWriteService).should(times(1)).create(createMentionCommand);
            then(mentionWriteService).should(times(1)).create(createMentionCommand2);
        }
    }
}
