package com.pellto.youtoy.channel.util;

import com.pellto.youtoy.channel.domain.model.ChannelInfo;

public class ChannelInfoFixtureFactory {

  private static final String DISPLAY_NAME = "displayName";
  private static final String DESCRIPTION = "description";
  private static final String BANNER_PATH = "bannerPath";
  private static final String PROFILE_PATH = "profilePath";

  public static ChannelInfo create() {
    return ChannelInfo.builder()
        .displayName(DISPLAY_NAME)
        .description(DESCRIPTION)
        .bannerPath(BANNER_PATH)
        .profilePath(PROFILE_PATH)
        .build();
  }
}
