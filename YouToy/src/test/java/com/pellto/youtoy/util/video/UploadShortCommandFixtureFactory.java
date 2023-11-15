package com.pellto.youtoy.util.video;

import com.pellto.youtoy.domain.video.dto.UploadShortCommand;

public class UploadShortCommandFixtureFactory {
    private static final Long CHANNEL_ID = 1L;
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";

    public static UploadShortCommand create() {
        return create(CHANNEL_ID, TITLE, DESCRIPTION);
    }

    public static UploadShortCommand create(Long channelId, String title, String description) {
        return new UploadShortCommand(channelId, title, description);
    }
}
