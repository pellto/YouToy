package com.pellto.youtoy.util.video;

import com.pellto.youtoy.domain.video.dto.UpdateShortCommand;
import com.pellto.youtoy.domain.video.dto.UploadShortCommand;
import com.pellto.youtoy.domain.video.entity.Shorts;

import java.time.LocalDateTime;

public class ShortFixtureFactory {
    private static final Long ID = 1L;
    private static final Long CHANNEL_ID = 1L;
    private static final String TITLE = "title";
    private static final Long VIEW_COUNT = 0L;
    private static final String DESCRIPTION = "description";
    private static final LocalDateTime CREATED_AT = LocalDateTime.of(
            2023, 1, 1, 0, 0, 0
    );

    static public Shorts create(UploadShortCommand cmd) {
        return Shorts.builder()
                .channelId(cmd.channelId())
                .title(cmd.title())
                .description(cmd.description())
                .build();
    }

    static public Shorts create(UpdateShortCommand cmd) {
        return Shorts.builder()
                .id(ID)
                .channelId(CHANNEL_ID)
                .title(cmd.title())
                .description(cmd.description())
                .viewCount(VIEW_COUNT)
                .createdAt(CREATED_AT)
                .build();
    }

    static public Shorts create() {
        return Shorts.builder()
                .id(ID)
                .channelId(CHANNEL_ID)
                .title(TITLE)
                .viewCount(VIEW_COUNT)
                .description(DESCRIPTION)
                .createdAt(CREATED_AT)
                .build();
    }
}
