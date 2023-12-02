package com.pellto.youtoy.domain.comment;

import com.pellto.youtoy.domain.comment.repository.MentionRepository;
import com.pellto.youtoy.domain.comment.service.MentionWriteService;
import com.pellto.youtoy.util.comment.CreateMentionCommandFixtureFactory;
import com.pellto.youtoy.util.comment.MentionFixtureFactory;
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
}
