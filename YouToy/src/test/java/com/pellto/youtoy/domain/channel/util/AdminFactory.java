package com.pellto.youtoy.domain.channel.util;

import com.pellto.youtoy.domain.channel.domain.Admin;
import com.pellto.youtoy.domain.channel.domain.AuthLevel;
import com.pellto.youtoy.domain.channel.domain.Channel;
import com.pellto.youtoy.domain.channel.dto.AdminDto;
import com.pellto.youtoy.domain.channel.dto.InviteAdminRequest;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import com.pellto.youtoy.global.util.RandomString;
import java.time.LocalDateTime;

public class AdminFactory {

  private static final Long ID = 1L;
  private static final Channel TARGET_CHANNEL = ChannelFactory.createChannel();
  private static final UserUUID ADMIN_UUID = new UserUUID(RandomString.make());
  private static final AuthLevel AUTH_LEVEL = AuthLevel.VIEWER;
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();


  public static Admin createAdmin() {
    return Admin.builder()
        .id(ID)
        .targetChannel(TARGET_CHANNEL)
        .adminUuid(ADMIN_UUID)
        .authLevel(AUTH_LEVEL)
        .createdAt(CREATED_AT)
        .build();
  }

  public static AdminDto createAdminDto() {
    return new AdminDto(ID, TARGET_CHANNEL.getId(), ADMIN_UUID.getValue(), AUTH_LEVEL);
  }

  public static Admin createBeforeSavedAdmin() {
    return Admin.builder()
        .targetChannel(TARGET_CHANNEL)
        .adminUuid(ADMIN_UUID)
        .authLevel(AUTH_LEVEL)
        .createdAt(CREATED_AT)
        .build();
  }

  public static InviteAdminRequest createInviteAdminRequest() {
    return new InviteAdminRequest(ADMIN_UUID.getValue(), TARGET_CHANNEL.getId(), AUTH_LEVEL);
  }
}
