package com.pellto.youtoy.application.usecase;

import com.pellto.youtoy.domain.channel.dto.DeleteChannelAdminCommand;
import com.pellto.youtoy.domain.channel.service.ChannelAdminWriteService;
import com.pellto.youtoy.domain.channel.service.ChannelReadService;
import com.pellto.youtoy.domain.user.service.UserReadService;
import com.pellto.youtoy.util.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteChannelAdminUsecase {

  private final ChannelAdminWriteService channelAdminWriteService;
  private final ChannelReadService channelReadService;
  private final UserReadService userReadService;

  public void execute(DeleteChannelAdminCommand cmd) {
    if (!channelReadService.isExist(cmd.channelId())) {
      throw new UnsupportedOperationException(ErrorCode.NOT_EXIST_CHANNEL.getMessage());
    }
    if (!userReadService.isExist(cmd.userId()) || !userReadService.isExist(cmd.ownerId())) {
      throw new UnsupportedOperationException(ErrorCode.NOT_EXIST_USER.getMessage());
    }
    if (!channelReadService.isOwner(cmd.channelId(), cmd.ownerId())) {
      throw new UnsupportedOperationException(ErrorCode.USER_IS_NOT_OWNER.getMessage());
    }
    // TODO: change to authority
    channelAdminWriteService.delete(cmd);
  }
}
