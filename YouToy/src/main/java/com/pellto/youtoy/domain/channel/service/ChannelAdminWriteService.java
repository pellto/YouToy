package com.pellto.youtoy.domain.channel.service;

import com.pellto.youtoy.domain.channel.dto.CreateChannelAdminCommand;
import com.pellto.youtoy.domain.channel.dto.DeleteChannelAdminCommand;
import com.pellto.youtoy.domain.channel.entity.ChannelAdmin;
import com.pellto.youtoy.domain.channel.repository.ChannelAdminRepository;
import com.pellto.youtoy.util.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChannelAdminWriteService {

  private final ChannelAdminRepository channelAdminRepository;

  public ChannelAdmin create(CreateChannelAdminCommand cmd) {
    if (channelAdminRepository.findByChannelIdAndUserId(cmd.channelId(), cmd.userId())
        .isPresent()) {
      throw new UnsupportedOperationException(ErrorCode.ALREADY_EXIST_ADMIN.getMessage());
    }
    var channelAdmin = ChannelAdmin
        .builder()
        .channelId(cmd.channelId())
        .userId(cmd.userId())
        .build();
    return channelAdminRepository.save(channelAdmin);
  }

  public void delete(DeleteChannelAdminCommand cmd) {
    if (channelAdminRepository.findByChannelIdAndUserId(cmd.channelId(), cmd.userId()).isEmpty()) {
      throw new UnsupportedOperationException(ErrorCode.NOT_EXIST_ADMIN.getMessage());
    }
    channelAdminRepository.delete(cmd.channelId(), cmd.userId());
  }
}
