package com.pellto.youtoy.util.view;

import com.pellto.youtoy.domain.view.dto.UpdateVideoCommand;

public class UpdateVideoCommandFixtureFactory {
    private static final Long ID = 1L;
    private static final Long USER_ID = 1L;
    private static final String TITLE = "changedTitle";
    private static final String DESCRIPTION = "changedDescription";

    public static UpdateVideoCommand create() {
        return create(ID, USER_ID, TITLE, DESCRIPTION);
    }

    public static UpdateVideoCommand create(Long id, Long userId, String title, String description) {
        return new UpdateVideoCommand(id, userId, title, description);
    }
}
