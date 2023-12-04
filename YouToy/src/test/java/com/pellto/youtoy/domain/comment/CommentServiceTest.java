package com.pellto.youtoy.domain.comment;

import com.pellto.youtoy.domain.comment.repository.CommentRepository;
import com.pellto.youtoy.domain.comment.service.CommentReadService;
import com.pellto.youtoy.domain.comment.service.CommentWriteService;
import com.pellto.youtoy.util.comment.CommentFixtureFactory;
import com.pellto.youtoy.util.comment.CommentDtoFixtureCommand;
import com.pellto.youtoy.util.comment.CreateCommentCommandFixtureFactory;
import com.pellto.youtoy.util.comment.UpdateCommentCommandFixtureFactory;
import com.pellto.youtoy.util.error.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@Tag("domain")
@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {
    private static final String DOMAIN = "Comment";
    @InjectMocks
    private CommentWriteService commentWriteService;
    @Mock
    private CommentReadService commentReadService;

    @Mock
    private CommentRepository commentRepository;

    @DisplayName("[" + DOMAIN + ": create: success] comment 생성 성공 테스트")
    @Test
    public void createTest() {
        var cmd = CreateCommentCommandFixtureFactory.create();
        var comment = CommentFixtureFactory.create(cmd);
        var commentDto = CommentDtoFixtureCommand.create(comment);

        given(commentRepository.save(any())).willReturn(comment);
        given(commentReadService.toDto(any())).willReturn(commentDto);

        var createdComment = commentWriteService.create(cmd);

        assertEquals(cmd.userId(), createdComment.userId());
        assertEquals(cmd.content(), createdComment.content());
        assertEquals(0, createdComment.replyCnt());
        then(commentRepository).should(times(1)).save(any());
        then(commentReadService).should(times(1)).toDto(any());
    }

    @DisplayName("[" + DOMAIN + ": update: success] comment 내용 변경 성공 테스트")
    @Test
    public void updateTest() {
        var cmd = UpdateCommentCommandFixtureFactory.create();
        var comment = CommentFixtureFactory.create(
                CreateCommentCommandFixtureFactory.create()
        );
        var commentDto = CommentDtoFixtureCommand.create(cmd.content());

        given(commentRepository.findById(any())).willReturn(Optional.of(comment));
        given(commentRepository.save(any())).willReturn(comment);
        given(commentReadService.toDto(any())).willReturn(commentDto);

        var updatedComment = commentWriteService.update(cmd);

        then(commentRepository).should(times(1)).save(any());
        then(commentRepository).should(times(1)).findById(any());
        then(commentReadService).should(times(1)).toDto(any());
        assertEquals(cmd.id(), updatedComment.id());
        assertEquals(cmd.content(), updatedComment.content());
    }

    @DisplayName("[" + DOMAIN + ": update: not exist comment] comment 미변경 성공 테스트")
    @Test
    public void updateNotExistTest() {
        var cmd = UpdateCommentCommandFixtureFactory.create(-1L);

        given(commentRepository.findById(any())).willReturn(null);

        try {
            commentWriteService.update(cmd);
        } catch (Exception e) {
            assertEquals(e.getClass(), NullPointerException.class);
            then(commentRepository).should(times(0)).save(any());
            then(commentRepository).should(times(1)).findById(any());
        }
    }

    @DisplayName("[" + DOMAIN + ": update: unchanged success] comment 미변경 성공 테스트")
    @Test
    public void updateUnchangedTest() {
        var comment = CommentFixtureFactory.create(
                CreateCommentCommandFixtureFactory.create()
        );
        var cmd = UpdateCommentCommandFixtureFactory.create(comment.getContent());
        var commentDto = CommentDtoFixtureCommand.create(comment);

        given(commentRepository.findById(any())).willReturn(Optional.of(comment));
        given(commentReadService.toDto(any())).willReturn(commentDto);

        var updatedComment = commentWriteService.update(cmd);

        then(commentRepository).should(times(0)).save(any());
        then(commentRepository).should(times(1)).findById(any());
        then(commentReadService).should(times(1)).toDto(any());
        assertEquals(cmd.id(), updatedComment.id());
        assertEquals(cmd.content(), updatedComment.content());
    }

    @DisplayName("[" + DOMAIN + ": delete: success] comment 삭제 성공 테스트")
    @Test
    public void deleteTest() {
        Long id = 1L;

        given(commentRepository.existById(any())).willReturn(true);

        commentWriteService.delete(id);

        then(commentRepository).should(times(1)).existById(any());
        then(commentRepository).should(times(1)).delete(any());
    }

    @DisplayName("[" + DOMAIN + ": delete: not exist comment] 존재하지 않는 comment 삭제 실패 테스트")
    @Test
    public void deleteNotExistCommentTest() {
        Long id = 1L;

        given(commentRepository.existById(any())).willReturn(false);

        try {
            commentWriteService.delete(id);
        } catch (Exception e) {
            assertEquals(ErrorCode.NOT_EXIST_COMMENT.getMessage(), e.getMessage());
            then(commentRepository).should(times(1)).existById(any());
            then(commentRepository).should(times(0)).delete(any());
        }
    }
}
