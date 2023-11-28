package com.pellto.youtoy.domain.comment;

import com.pellto.youtoy.domain.comment.dto.CreateCommentCommand;
import com.pellto.youtoy.domain.comment.repository.CommentRepository;
import com.pellto.youtoy.domain.comment.service.CommentWriteService;
import com.pellto.youtoy.util.comment.CommandFixtureFactory;
import com.pellto.youtoy.util.comment.CreateCommentCommandFixtureFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@Tag("domain")
@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {
    private static final String DOMAIN = "Comment";
    @InjectMocks
    private CommentWriteService commentWriteService;

    @Mock
    private CommentRepository commentRepository;

    @DisplayName("[" + DOMAIN + ": create: success] comment 생성 성공 테스트")
    @Test
    public void createTest() {
        var cmd = CreateCommentCommandFixtureFactory.create();
        var comment = CommandFixtureFactory.create(cmd);

        given(commentRepository.save(any())).willReturn(comment);

        var createdComment = commentWriteService.create(cmd);

        assertEquals(cmd.videoId(), createdComment.getVideoId());
        assertEquals(cmd.userId(), createdComment.getUserId());
        assertEquals(cmd.video(), createdComment.isVideo());
        assertEquals(cmd.content(), createdComment.getContent());
        assertEquals(cmd.repliedCommentId(), createdComment.getRepliedCommentId());
        then(commentRepository).should(times(1)).save(any());
    }
}
