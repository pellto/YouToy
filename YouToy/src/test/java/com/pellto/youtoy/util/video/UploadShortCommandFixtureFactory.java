package com.pellto.youtoy.util.video;

import com.pellto.youtoy.domain.view.dto.UploadShortCommand;

public class UploadShortCommandFixtureFactory {
    private static final Long CHANNEL_ID = 1L;
    private static final Long USER_ID = 1L;
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";

    public static UploadShortCommand create() {
        return create(CHANNEL_ID, USER_ID, TITLE, DESCRIPTION);
    }

    public static UploadShortCommand create(Long channelId, Long userId, String title, String description) {
        return new UploadShortCommand(channelId, userId, title, description);
    }
}
