package com.pellto.youtoy.util.comment;

import com.pellto.youtoy.domain.comment.dto.CreateCommentCommand;

import java.time.LocalDateTime;

public class CreateCommentCommandFixtureFactory {
    private static final Long VIDEO_ID = 1L;
    private static final Long USER_ID = 1L;
    private static final Long REPLIED_COMMENT_ID = 1L;
    private static final boolean VIDEO = true;
    private static final String CONTENT = "content";
    private static final String MENTIONED_CHANNEL_HANDLE = "test-handle";

    public static CreateCommentCommand create() {
        return create(VIDEO_ID, USER_ID, REPLIED_COMMENT_ID, VIDEO, CONTENT, MENTIONED_CHANNEL_HANDLE);

    }

    public static CreateCommentCommand create(
            Long videoId,
            Long userId,
            Long repliedCommentId,
            boolean video,
            String content,
            String mentionedChannelHandle
    ) {
        return new CreateCommentCommand(videoId, userId, video, content, repliedCommentId, mentionedChannelHandle);
    }
}
