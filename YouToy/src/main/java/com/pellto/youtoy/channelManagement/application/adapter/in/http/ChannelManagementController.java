package com.pellto.youtoy.channelManagement.application.adapter.in.http;

import com.pellto.youtoy.channelManagement.domain.port.in.ChangeManagementUsecase;
import com.pellto.youtoy.channelManagement.domain.port.in.CreateManagementUsecase;
import com.pellto.youtoy.global.dto.channelManagement.ChannelManagementDto;
import com.pellto.youtoy.global.dto.channelManagement.request.ChangeChannelManagementRequest;
import com.pellto.youtoy.global.dto.channelManagement.request.CreateChannelManagementRequest;
import com.pellto.youtoy.global.dto.channelManagement.response.ChangeChannelManagementResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/channelManagements")
@RequiredArgsConstructor
public class ChannelManagementController {

  private final CreateManagementUsecase createManagementUsecase;
  private final ChangeManagementUsecase changeManagementUsecase;

  // TODO: Check auth token with correct channel authorization
  @PostMapping("/invite")
  public ChannelManagementDto inviteMember(@RequestBody CreateChannelManagementRequest request) {
    return createManagementUsecase.invite(request.channelId(), request.memberId());
  }

  @PatchMapping
  public ChangeChannelManagementResponse changeLevel(
      @RequestBody ChangeChannelManagementRequest request) {
    return changeManagementUsecase.changeLevel(request.channelId(), request.memberId(),
        request.level());
  }
}
