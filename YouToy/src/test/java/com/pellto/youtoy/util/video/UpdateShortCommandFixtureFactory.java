package com.pellto.youtoy.util.video;

import com.pellto.youtoy.domain.view.dto.UpdateShortCommand;

public class UpdateShortCommandFixtureFactory {
    private static final Long ID = 1L;
    private static final Long USER_ID = 1L;
    private static final String TITLE = "changedTitle";
    private static final String DESCRIPTION = "changedDescription";

    public static UpdateShortCommand create() {
        return create(ID, USER_ID, TITLE, DESCRIPTION);
    }

    public static UpdateShortCommand create(Long id, Long userId, String title, String description) {
        return new UpdateShortCommand(id, userId, title, description);
    }
}
