package com.pellto.youtoy.domain.comment;

import com.pellto.youtoy.domain.comment.entity.Mention;
import com.pellto.youtoy.domain.comment.repository.MentionRepository;
import com.pellto.youtoy.domain.comment.service.MentionWriteService;
import com.pellto.youtoy.util.comment.CreateMentionCommandFixtureFactory;
import com.pellto.youtoy.util.comment.MentionFixtureFactory;
import com.pellto.youtoy.util.error.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@Tag("domain")
@ExtendWith(MockitoExtension.class)
public class MentionServiceTest {
    private static final String DOMAIN = "Mention";
    @InjectMocks
    private MentionWriteService mentionWriteService;
    @Mock
    private MentionRepository mentionRepository;

    @DisplayName("[" + DOMAIN + ": create: success] mention 생성 성공 테스트")
    @Test
    public void createTest() {
        var cmd = CreateMentionCommandFixtureFactory.create();
        var mention = MentionFixtureFactory.create();

        given(mentionRepository.save(any())).willReturn(mention);

        var createdMention = mentionWriteService.create(cmd);

        assertEquals(cmd.commentId(), createdMention.getCommentId());
        assertEquals(cmd.mentionedChannelId(), createdMention.getMentionedChannelId());
        assertEquals(cmd.createdAt(), createdMention.getCreatedAt());
        then(mentionRepository).should(times(1)).save(any());
    }

    @DisplayName("[" + DOMAIN + ": delete: success] mention 삭제 성공 테스트")
    @Test
    public void deleteTest() {
        Long id = 1L;

        given(mentionRepository.existByCommentId(any())).willReturn(true);

        mentionWriteService.deleteByCommentId(id);

        then(mentionRepository).should(times(1)).existByCommentId(any());
        then(mentionRepository).should(times(1)).deleteByCommentId(any());
    }

    @DisplayName("[" + DOMAIN + ": delete: not exist mention] 존재하지 않는 mention 삭제 실패 테스트")
    @Test
    public void deleteNotExistTest() {
        Long id = -1L;

        given(mentionRepository.existByCommentId(any())).willReturn(false);

        try {
            mentionWriteService.deleteByCommentId(id);
        } catch (Exception e) {
            assertEquals(ErrorCode.NOT_EXIST_MENTION.getMessage(), e.getMessage());
            then(mentionRepository).should(times(1)).existByCommentId(any());
            then(mentionRepository).should(times(0)).deleteByCommentId(any());
        }
    }
}
