package com.pellto.youtoy.domain.channel.util;

import com.pellto.youtoy.domain.channel.domain.Channel;
import com.pellto.youtoy.domain.channel.dto.ChannelDto;
import com.pellto.youtoy.domain.channel.dto.CreateChannelRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ChannelFactory {

  private static final Long ID = 1L;
  private static final String HANDLE = "@test-handle";
  private static final String DISPLAY_NAME = "displayName";
  private static final String DESCRIPTION = "description";
  private static final String BANNER = "banner";
  private static final String PROFILE = "profile";
  private static final Long SUBSCRIBER_COUNT = 0L;
  private static final List<Long> SUBSCRIBED_LIST = new ArrayList<>();
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();
  private static final LocalDateTime MODIFIED_AT = CREATED_AT;

  // for subscribe
  private static final Long SUBSCRIBER_ID = 2L;
  private static final Long SUBSCRIBED_ID = 3L;

  public static Channel createBeforeSavedChannel() {
    return Channel.builder()
        .handle(HANDLE)
        .displayName(DISPLAY_NAME)
        .description(DESCRIPTION)
        .banner(BANNER)
        .profile(PROFILE)
        .build();
  }

  public static Channel createChannel() {
    return Channel.builder()
        .id(ID)
        .handle(HANDLE)
        .displayName(DISPLAY_NAME)
        .description(DESCRIPTION)
        .banner(BANNER)
        .profile(PROFILE)
        .createdAt(CREATED_AT)
        .modifiedAt(MODIFIED_AT)
        .build();
  }

  public static Channel createChannel(Long id) {
    return Channel.builder()
        .id(id)
        .handle(HANDLE)
        .displayName(DISPLAY_NAME)
        .description(DESCRIPTION)
        .banner(BANNER)
        .profile(PROFILE)
        .createdAt(CREATED_AT)
        .modifiedAt(MODIFIED_AT)
        .build();
  }

  public static ChannelDto createChannelDto() {
    return new ChannelDto(
        ID,
        HANDLE,
        DISPLAY_NAME,
        DESCRIPTION,
        BANNER,
        PROFILE,
        SUBSCRIBER_COUNT,
        SUBSCRIBED_LIST,
        CREATED_AT
    );
  }

  public static CreateChannelRequest createChannelRequest() {
    return new CreateChannelRequest(
        HANDLE,
        DISPLAY_NAME,
        DESCRIPTION,
        BANNER,
        PROFILE
    );
  }

  public static Channel createSubscriber() {
    return Channel.builder()
        .id(SUBSCRIBER_ID)
        .handle(HANDLE)
        .displayName(DISPLAY_NAME)
        .description(DESCRIPTION)
        .banner(BANNER)
        .profile(PROFILE)
        .createdAt(CREATED_AT)
        .modifiedAt(MODIFIED_AT)
        .build();
  }

  public static Channel createSubscribed() {
    return Channel.builder()
        .id(SUBSCRIBED_ID)
        .handle(HANDLE)
        .displayName(DISPLAY_NAME)
        .description(DESCRIPTION)
        .banner(BANNER)
        .profile(PROFILE)
        .createdAt(CREATED_AT)
        .modifiedAt(MODIFIED_AT)
        .build();
  }
}
