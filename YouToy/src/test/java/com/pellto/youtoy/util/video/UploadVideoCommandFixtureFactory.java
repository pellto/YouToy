package com.pellto.youtoy.util.video;

import com.pellto.youtoy.domain.video.dto.UploadVideoCommand;

public class UploadVideoCommandFixtureFactory {
    private static final Long CHANNEL_ID = 1L;
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";

    public static UploadVideoCommand create() {
        return create(CHANNEL_ID, TITLE, DESCRIPTION);
    }

    public static UploadVideoCommand create(Long channelId, String title, String description) {
        return new UploadVideoCommand(channelId, title, description);
    }
}
