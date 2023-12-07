package com.pellto.youtoy.util.playlist;

import com.pellto.youtoy.domain.playlist.dto.UpdatePlaylistCommand;

public class UpdatePlaylistCommandFixtureFactory {

  private static final Long ID = 1L;
  private static final String TITLE = "Changed title";
  private static final Integer TARGET_RANGE = 2;

  public static UpdatePlaylistCommand create() {
    return create(ID, TITLE, TARGET_RANGE);
  }

  public static UpdatePlaylistCommand create(Long id) {
    return create(id, TITLE, TARGET_RANGE);
  }

  public static UpdatePlaylistCommand create(String title, Integer targetRange) {
    return create(ID, title, targetRange);
  }

  public static UpdatePlaylistCommand create(
      Long id,
      String title,
      Integer targetRange
  ) {
    return new UpdatePlaylistCommand(id, title, targetRange);
  }
}
