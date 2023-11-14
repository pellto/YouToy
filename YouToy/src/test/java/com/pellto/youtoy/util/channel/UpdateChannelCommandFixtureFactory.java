package com.pellto.youtoy.util.channel;


import com.pellto.youtoy.domain.channel.dto.UpdateChannelCommand;

public class UpdateChannelCommandFixtureFactory {
    private static final Long ID = 1L;
    private static final String HANDLE = "changedHandle";
    private static final String DISPLAY_NAME = "changedDisplayName";
    private static final String DESCRIPTION = "changedDescription";
    private static final String BANNER = "changedBanner";
    private static final String PROFILE = "changedProfile";

    public static UpdateChannelCommand get() {
        return get(ID, HANDLE, DISPLAY_NAME, DESCRIPTION, BANNER, PROFILE);
    }

    public static UpdateChannelCommand get(
            Long id,
            String handle,
            String displayName,
            String description,
            String banner,
            String profile
    ) {
        return new UpdateChannelCommand(
                id,
                handle,
                displayName,
                description,
                banner,
                profile
        );
    }

    public static UpdateChannelCommand getWithHandle(String handle) {
        return get(ID, handle, DISPLAY_NAME, DESCRIPTION, BANNER, PROFILE);
    }
}
