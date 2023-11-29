package com.pellto.youtoy.util.playlist;

import com.pellto.youtoy.domain.playlist.entity.Playlist;

import java.time.LocalDateTime;

public class PlaylistFixtureFactory {
    private static final Long ID = 1L;
    private static final Long CHANNEL_ID = 1L;
    private static final Integer TARGET_RANGE = 0;
    private static final String TITLE = "title";
    private static final LocalDateTime CREATED_AT = LocalDateTime.of(
            2023, 1, 1, 0, 0, 0
    );

    public static Playlist create() {
        return create(ID, CHANNEL_ID, TARGET_RANGE, TITLE, CREATED_AT);
    }

    public static Playlist create(Integer targetRange, String title) {
        return create(ID, CHANNEL_ID, targetRange, title, CREATED_AT);
    }

    public static Playlist create(String title) {
        return create(ID, CHANNEL_ID, TARGET_RANGE, title, CREATED_AT);
    }

    public static Playlist create(Integer targetRange) {
        return create(ID, CHANNEL_ID, targetRange, TITLE, CREATED_AT);
    }

    public static Playlist create(
            Long id,
            Long channelId,
            Integer targetRange,
            String title,
            LocalDateTime createdAt
    ) {
        return Playlist
                .builder()
                .id(id)
                .channelId(channelId)
                .targetRange(targetRange)
                .title(title)
                .createdAt(createdAt)
                .build();
    }
}
