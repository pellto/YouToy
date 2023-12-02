package com.pellto.youtoy.util.comment;

import com.pellto.youtoy.domain.comment.dto.CommentDto;
import com.pellto.youtoy.domain.comment.entity.Comment;

import java.time.LocalDateTime;

public class CommentDtoFixtureCommand {
    private static final Long ID = 1L;
    private static final Long USER_ID = 1L;
    private static final String CONTENT = "content";
    private static final Long REPLY_CNT = 0L;
    private static final LocalDateTime CREATED_AT = LocalDateTime.of(
            2023, 1, 1, 0, 0, 0
    );

    public static CommentDto create(Comment comment) {
        return create(comment.getId(), comment.getUserId(), comment.getContent(), REPLY_CNT, comment.getCreatedAt());
    }

    public static CommentDto create() {
        return create(ID, USER_ID, CONTENT, REPLY_CNT, CREATED_AT);
    }

    public static CommentDto create(
            Long id,
            Long userId,
            String content,
            Long replyCnt,
            LocalDateTime createdAt
    ) {
        return new CommentDto(id, userId, content, replyCnt, createdAt);
    }

    public static CommentDto create(String content) {
        return create(ID, USER_ID, content, REPLY_CNT, CREATED_AT);
    }
}
