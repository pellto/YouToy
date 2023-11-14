package com.pellto.youtoy.util.video;

import com.pellto.youtoy.domain.video.dto.UpdateVideoCommand;

public class UpdateVideoCommandFixtureFactory {
    private static final Long ID = 1L;
    private static final String TITLE = "changedTitle";
    private static final String DESCRIPTION = "changedDescription";

    public static UpdateVideoCommand create() {
        return create(ID, TITLE, DESCRIPTION);
    }

    public static UpdateVideoCommand create(Long id, String title, String description) {
        return new UpdateVideoCommand(id, title, description);
    }
}
