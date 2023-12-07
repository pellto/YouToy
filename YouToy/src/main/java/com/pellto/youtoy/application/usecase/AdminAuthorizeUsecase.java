package com.pellto.youtoy.application.usecase;

import com.pellto.youtoy.domain.channel.service.ChannelAdminReadService;
import com.pellto.youtoy.domain.channel.service.ChannelReadService;
import com.pellto.youtoy.domain.user.service.UserReadService;
import com.pellto.youtoy.domain.view.dto.AbVideoCommand;
import com.pellto.youtoy.util.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminAuthorizeUsecase {

  private final UserReadService userReadService;
  private final ChannelReadService channelReadService;
  private final ChannelAdminReadService channelAdminReadService;

  public Boolean execute(AbVideoCommand cmd) {
    if (!channelReadService.isExist(cmd.channelId())) {
      throw new UnsupportedOperationException(ErrorCode.NOT_EXIST_CHANNEL.getMessage());
    }
    if (!userReadService.isExist(cmd.userId())) {
      throw new UnsupportedOperationException(ErrorCode.NOT_EXIST_USER.getMessage());
    }
    if (!channelReadService.isOwner(cmd.channelId(), cmd.userId())
        && !channelAdminReadService.isAdmin(cmd.channelId(), cmd.userId())) {
      throw new UnsupportedOperationException(ErrorCode.NOT_AUTHORIZED_USER.getMessage());
    }
    return true;
  }
}
