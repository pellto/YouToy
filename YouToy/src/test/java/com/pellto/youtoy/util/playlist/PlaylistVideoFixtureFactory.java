package com.pellto.youtoy.util.playlist;

import com.pellto.youtoy.domain.playlist.dto.PlaylistVideoDto;
import com.pellto.youtoy.domain.playlist.entity.PlaylistVideo;

import java.time.LocalDateTime;

public class PlaylistVideoFixtureFactory {
    private static final Long ID = 1L;
    private static final Long PLAYLIST_ID = 1L;
    private static final Long VIDEO_ID = 1L;
    private static final Integer VIDEO_TYPE = 0;
    private static final LocalDateTime CREATED_AT = LocalDateTime.of(
            2023, 1, 1, 0, 0
    );

    public static PlaylistVideo create() {
        return create(ID, PLAYLIST_ID, VIDEO_ID, VIDEO_TYPE, CREATED_AT);
    }

    public static PlaylistVideo create(Integer videoType) {
        return create(ID, PLAYLIST_ID, VIDEO_ID, videoType, CREATED_AT);
    }

    private static PlaylistVideo create(
            Long id,
            Long playlistId,
            Long videoId,
            Integer videoType,
            LocalDateTime createdAt
    ) {
        return PlaylistVideo
                .builder()
                .id(id)
                .playlistId(playlistId)
                .videoId(videoId)
                .videoType(videoType)
                .createdAt(createdAt)
                .build();
    }

    public static PlaylistVideoDto toDto(PlaylistVideo playlistVideo) {
        return new PlaylistVideoDto(
                playlistVideo.getId(),
                playlistVideo.getPlaylistId(),
                playlistVideo.getVideoId(),
                playlistVideo.getVideoType()
        );
    }
}
