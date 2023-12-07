package com.pellto.youtoy.util.playlist;

import com.pellto.youtoy.domain.playlist.dto.CreatePlaylistCommand;

public class CreatePlaylistCommandFixtureFactory {

  private static final Long CHANNEL_ID = 1L;
  private static final String TITLE = "title";
  private static final Integer TARGET_RANGE = 0;

  public static CreatePlaylistCommand create() {
    return create(CHANNEL_ID, TITLE, TARGET_RANGE);
  }

  public static CreatePlaylistCommand create(
      Long channelId,
      String title,
      Integer targetRange
  ) {
    return new CreatePlaylistCommand(channelId, title, targetRange);
  }
}
