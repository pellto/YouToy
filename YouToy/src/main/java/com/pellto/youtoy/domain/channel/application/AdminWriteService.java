package com.pellto.youtoy.domain.channel.application;

import com.pellto.youtoy.domain.channel.domain.Admin;
import com.pellto.youtoy.domain.channel.dto.AdminDto;
import com.pellto.youtoy.domain.channel.dto.InviteAdminRequest;
import com.pellto.youtoy.domain.channel.exception.NotExistChannelException;
import com.pellto.youtoy.domain.channel.repository.AdminRepository;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminWriteService {

  private final AdminRepository adminRepository;
  private final AdminReadService adminReadService;
  private final ChannelReadService channelReadService;

  public void emit(Long id) {
    adminRepository.deleteById(id);
  }

  public AdminDto invite(InviteAdminRequest req) {
    if (!channelReadService.existById(req.targetChannelId())) {
      throw new NotExistChannelException();
    }
    var targetchannel = channelReadService.getById(req.targetChannelId());
    var adminUuid = new UserUUID(req.userUuid());
    var admin = Admin.builder()
        .targetChannel(targetchannel)
        .adminUuid(adminUuid)
        .authLevel(req.authLevel())
        .build();
    return adminReadService.toDto(adminRepository.save(admin));
  }

}
