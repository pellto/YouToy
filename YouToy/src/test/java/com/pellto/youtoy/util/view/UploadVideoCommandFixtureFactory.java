package com.pellto.youtoy.util.view;

import com.pellto.youtoy.domain.view.dto.UploadVideoCommand;

public class UploadVideoCommandFixtureFactory {
    private static final Long CHANNEL_ID = 1L;
    private static final Long USER_ID = 1L;
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";

    public static UploadVideoCommand create() {
        return create(CHANNEL_ID, USER_ID, TITLE, DESCRIPTION);
    }

    public static UploadVideoCommand create(Long channelId, Long userId, String title, String description) {
        return new UploadVideoCommand(channelId, userId, title, description);
    }
}
