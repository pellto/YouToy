package com.pellto.youtoy.application.controller;

import com.pellto.youtoy.application.usecase.CreateChannelAdminUsecase;
import com.pellto.youtoy.application.usecase.DeleteChannelAdminUsecase;
import com.pellto.youtoy.domain.channel.dto.ChannelDto;
import com.pellto.youtoy.domain.channel.dto.CreateChannelAdminCommand;
import com.pellto.youtoy.domain.channel.dto.CreateChannelCommand;
import com.pellto.youtoy.domain.channel.dto.DeleteChannelAdminCommand;
import com.pellto.youtoy.domain.channel.dto.UpdateChannelCommand;
import com.pellto.youtoy.domain.channel.entity.Channel;
import com.pellto.youtoy.domain.channel.service.ChannelReadService;
import com.pellto.youtoy.domain.channel.service.ChannelWriteService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/channels")
public class ChannelController {

  private final ChannelWriteService channelWriteService;
  private final ChannelReadService channelReadService;
  private final CreateChannelAdminUsecase createChannelAdminUsecase;
  private final DeleteChannelAdminUsecase deleteChannelAdminUsecase;

  @PostMapping
  public Channel create(@RequestBody CreateChannelCommand cmd) {
    return channelWriteService.create(cmd);
  }

  @PostMapping("/admin")
  public void create(@RequestBody CreateChannelAdminCommand cmd) {
    createChannelAdminUsecase.execute(cmd);
  }

  @DeleteMapping("/admin")
  public void delete(@RequestBody DeleteChannelAdminCommand cmd) {
    deleteChannelAdminUsecase.execute(cmd);
  }

  @GetMapping("/{id}")
  public ChannelDto getChannel(@PathVariable Long id) {
    return channelReadService.getChannel(id);
  }

  @PutMapping
  public ChannelDto update(@Valid @RequestBody UpdateChannelCommand cmd) {
    return channelReadService.toDto(channelWriteService.update(cmd));
  }
}
