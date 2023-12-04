package com.pellto.youtoy.util.view;

import com.pellto.youtoy.domain.view.dto.UpdateVideoCommand;
import com.pellto.youtoy.domain.view.dto.UploadVideoCommand;
import com.pellto.youtoy.domain.view.entity.Video;

import java.time.LocalDateTime;

public class VideoFixtureFactory {
    private static final Long ID = 1L;
    private static final Long CHANNEL_ID = 1L;
    private static final String TITLE = "title";
    private static final Long VIEW_COUNT = 0L;
    private static final String DESCRIPTION = "description";
    private static final LocalDateTime CREATED_AT = LocalDateTime.of(
            2023, 1, 1, 0, 0, 0
    );

    static public Video create(UploadVideoCommand cmd) {
        return Video.builder()
                .channelId(cmd.channelId())
                .title(cmd.title())
                .description(cmd.description())
                .build();
    }

    static public Video create(UpdateVideoCommand cmd) {
        return Video.builder()
                .id(ID)
                .channelId(CHANNEL_ID)
                .title(cmd.title())
                .description(cmd.description())
                .viewCount(VIEW_COUNT)
                .createdAt(CREATED_AT)
                .build();
    }

    static public Video create() {
        return Video.builder()
                .id(ID)
                .channelId(CHANNEL_ID)
                .title(TITLE)
                .viewCount(VIEW_COUNT)
                .description(DESCRIPTION)
                .createdAt(CREATED_AT)
                .build();
    }
}
