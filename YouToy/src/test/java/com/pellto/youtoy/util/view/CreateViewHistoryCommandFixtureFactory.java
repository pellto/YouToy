package com.pellto.youtoy.util.view;

import com.pellto.youtoy.domain.view.dto.CreateViewHistoryCommand;

public class CreateViewHistoryCommandFixtureFactory {
    private static final Long USER_ID = 1L;
    private static final Long VIDEO_ID = 1L;
    private static final Integer VIDEO_TYPE = 0;
    private static final Long LAST_VIEW_AT = 100L;

    public static CreateViewHistoryCommand create() {
        return create(USER_ID, VIDEO_ID, VIDEO_TYPE, LAST_VIEW_AT);
    }

    private static CreateViewHistoryCommand create(Long userId, Long videoId, Integer videoType, Long lastViewAt) {
        return new CreateViewHistoryCommand(userId, videoId, videoType, lastViewAt);
    }
}
