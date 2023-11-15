package com.pellto.youtoy.util.video;

import com.pellto.youtoy.domain.video.dto.UpdateShortCommand;

public class UpdateShortCommandFixtureFactory {
    private static final Long ID = 1L;
    private static final String TITLE = "changedTitle";
    private static final String DESCRIPTION = "changedDescription";

    public static UpdateShortCommand create() {
        return create(ID, TITLE, DESCRIPTION);
    }

    public static UpdateShortCommand create(Long id, String title, String description) {
        return new UpdateShortCommand(id, title, description);
    }
}
