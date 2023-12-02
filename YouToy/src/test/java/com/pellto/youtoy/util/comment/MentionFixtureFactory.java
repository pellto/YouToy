package com.pellto.youtoy.util.comment;

import com.pellto.youtoy.domain.comment.entity.Mention;

import java.time.LocalDateTime;

public class MentionFixtureFactory {
    private static final Long ID = 1L;
    private static final Long COMMENT_ID = 1L;
    private static final Long MENTIONED_CHANNEL_ID = 1L;
    private static final LocalDateTime CREATED_AT = LocalDateTime.of(
            2023, 1, 1, 0, 0, 0
    );

    public static Mention create() {
        return create(ID, COMMENT_ID, MENTIONED_CHANNEL_ID, CREATED_AT);
    }

    public static Mention create(
            Long id,
            Long commentId,
            Long mentionedChannelId,
            LocalDateTime createdAt
    ) {
        return Mention.builder()
                .id(id)
                .commentId(commentId)
                .mentionedChannelId(mentionedChannelId)
                .createdAt(createdAt)
                .build();
    }
}
