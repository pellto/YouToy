package com.pellto.youtoy.util.channel;

import com.pellto.youtoy.domain.channel.dto.ChannelDto;
import com.pellto.youtoy.domain.channel.dto.CreateChannelCommand;
import com.pellto.youtoy.domain.channel.entity.Channel;
import java.time.LocalDateTime;

public class ChannelFixtureFactory {

  private static final Long TEST_ID = 1L;
  private static final Long OWNER_ID = 1L;
  private static final String DISPLAY_NAME = "displayName";
  private static final LocalDateTime CREATED_AT = LocalDateTime.of(
      2023, 1, 1, 0, 0, 0
  );

  public static Channel create() {
    return create(TEST_ID, OWNER_ID, DISPLAY_NAME, CREATED_AT);
  }

  public static Channel create(String channelHandle) {
    return create(TEST_ID, OWNER_ID, DISPLAY_NAME, channelHandle, CREATED_AT);
  }

  public static Channel create(Long channelId) {
    return create(channelId, OWNER_ID, DISPLAY_NAME, CREATED_AT);
  }

  public static Channel create(
      Long testId,
      Long ownerId,
      String displayName,
      LocalDateTime createdAt
  ) {
    return Channel.builder()
        .id(testId)
        .ownerId(ownerId)
        .displayName(displayName)
        .createdAt(createdAt)
        .build();
  }

  private static Channel create(
      Long testId,
      Long ownerId,
      String displayName,
      String channelHandle,
      LocalDateTime createdAt
  ) {
    return Channel.builder()
        .id(testId)
        .ownerId(ownerId)
        .displayName(displayName)
        .handle(channelHandle)
        .createdAt(createdAt)
        .build();
  }

  public static Channel create(CreateChannelCommand cmd) {
    return create(TEST_ID, cmd.ownerId(), cmd.displayName(), CREATED_AT);
  }

  public static ChannelDto toDto(Channel channel) {
    return new ChannelDto(channel.getId(), channel.getOwnerId(), channel.getHandle(),
        channel.getDisplayName());
  }
}
