package com.pellto.youtoy.util.playlist;

import com.pellto.youtoy.domain.playlist.dto.CreatePlaylistVideoCommand;

public class CreatePlaylistVideoCommandFixtureFactory {
    private static final Long PLAYLIST_ID = 1L;
    private static final Long VIDEO_ID = 1L;
    private static final Integer VIDEO_TYPE = 0;

    public static CreatePlaylistVideoCommand create() {
        return create(PLAYLIST_ID, VIDEO_ID, VIDEO_TYPE);
    }

    private static CreatePlaylistVideoCommand create(
            Long playlistId,
            Long videoId,
            Integer videoType
    ) {
        return new CreatePlaylistVideoCommand(playlistId, videoId, videoType);
    }
}
