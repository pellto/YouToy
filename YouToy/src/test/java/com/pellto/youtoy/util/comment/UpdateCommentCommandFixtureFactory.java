package com.pellto.youtoy.util.comment;

import com.pellto.youtoy.domain.comment.dto.UpdateCommentCommand;

public class UpdateCommentCommandFixtureFactory {
    private static final Long ID = 1L;
    private static final String CONTENT = "updated_content";
    private static final String MENTIONED_CHANNEL_HANDLE = "test-handle";

    public static UpdateCommentCommand create() {
        return create(ID, CONTENT, MENTIONED_CHANNEL_HANDLE);
    }

    public static UpdateCommentCommand create(Long id, String content, String mentionedChannelHandle) {
        return new UpdateCommentCommand(id, content, mentionedChannelHandle);
    }

    public static UpdateCommentCommand create(String content) {
        return new UpdateCommentCommand(ID, content, MENTIONED_CHANNEL_HANDLE);
    }

    public static UpdateCommentCommand create(Long id) {
        return new UpdateCommentCommand(id, CONTENT, MENTIONED_CHANNEL_HANDLE);
    }
}
