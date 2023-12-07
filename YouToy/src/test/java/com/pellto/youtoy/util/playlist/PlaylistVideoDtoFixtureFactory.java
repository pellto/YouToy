package com.pellto.youtoy.util.playlist;

import com.pellto.youtoy.domain.playlist.dto.PlaylistVideoDto;

public class PlaylistVideoDtoFixtureFactory {

  private static final Long ID = 1L;
  private static final Long PLAYLIST_ID = 1L;
  private static final Long VIDEO_ID = 1L;
  private static final Integer VIDEO_TYPE = 0;

  public static PlaylistVideoDto create() {
    return create(ID, PLAYLIST_ID, VIDEO_ID, VIDEO_TYPE);
  }

  public static PlaylistVideoDto create(Integer videoType) {
    return create(ID, PLAYLIST_ID, VIDEO_ID, videoType);
  }

  private static PlaylistVideoDto create(
      Long id,
      Long playlistId,
      Long videoId,
      Integer videoType
  ) {
    return new PlaylistVideoDto(id, playlistId, videoId, videoType);
  }
}
