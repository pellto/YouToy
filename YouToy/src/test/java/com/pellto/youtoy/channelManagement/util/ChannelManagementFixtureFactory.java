package com.pellto.youtoy.channelManagement.util;

import com.pellto.youtoy.channelManagement.domain.model.ChannelManagement;
import com.pellto.youtoy.channelManagement.domain.model.ChannelManagementLevel;
import com.pellto.youtoy.global.util.General;
import java.time.LocalDateTime;

public class ChannelManagementFixtureFactory {

  private static final Long ID = 1L;
  private static final Long CHANNEL_ID = 1L;
  private static final Long MEMBER_ID = 1L;
  private static final ChannelManagementLevel CHANNEL_MANAGEMENT_LEVEL = ChannelManagementLevel.VIEWER;
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();

  public static ChannelManagement create() {
    return ChannelManagement.builder()
        .id(ID)
        .channelId(CHANNEL_ID)
        .memberId(MEMBER_ID)
        .manageLevel(CHANNEL_MANAGEMENT_LEVEL)
        .createdAt(CREATED_AT)
        .build();
  }

  public static ChannelManagement create(ChannelManagement before) {
    return ChannelManagement.builder()
        .id(ID)
        .channelId(before.getChannelId())
        .memberId(before.getMemberId())
        .manageLevel(General.setNullInput(before.getManageLevel(), CHANNEL_MANAGEMENT_LEVEL))
        .createdAt(CREATED_AT)
        .build();
  }

  public static ChannelManagement createBeforeSaved() {
    return ChannelManagement.builder()
        .channelId(CHANNEL_ID)
        .memberId(MEMBER_ID)
        .build();
  }

  public static ChannelManagement createBeforeSavedWithOwner() {
    return ChannelManagement.builder()
        .channelId(CHANNEL_ID)
        .memberId(MEMBER_ID)
        .manageLevel(ChannelManagementLevel.OWNER)
        .build();
  }
}
