package com.pellto.youtoy.auth.util;

import com.pellto.youtoy.global.dto.channelManagement.ChannelManagementDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ChannelManagementDtoFixtureFactory {

  private static final Long ID = 1L;
  private static final Long CHANNEL_ID = 1L;
  private static final Long MEMBER_ID = 1L;
  private static final String MANAGE_LEVEL = "OWNER";
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();

  public static ChannelManagementDto create() {
    return new ChannelManagementDto(ID, CHANNEL_ID, MEMBER_ID, MANAGE_LEVEL, CREATED_AT);
  }

  public static List<ChannelManagementDto> createList() {
    var channelManagements = new ArrayList<ChannelManagementDto>();
    channelManagements.add(create());
    return channelManagements;
  }
}
