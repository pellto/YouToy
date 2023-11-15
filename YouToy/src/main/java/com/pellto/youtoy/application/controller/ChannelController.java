package com.pellto.youtoy.application.controller;

import com.pellto.youtoy.application.usecase.CreateChannelAdminUsecase;
import com.pellto.youtoy.application.usecase.DeleteChannelAdminUsecase;
import com.pellto.youtoy.domain.channel.dto.*;
import com.pellto.youtoy.domain.channel.entity.Channel;
import com.pellto.youtoy.domain.channel.entity.ChannelAdmin;
import com.pellto.youtoy.domain.channel.service.ChannelAdminWriteService;
import com.pellto.youtoy.domain.channel.service.ChannelReadService;
import com.pellto.youtoy.domain.channel.service.ChannelWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping("/{id}")
    public ChannelDto getChannel(@PathVariable Long id) {
        return channelReadService.getChannel(id);
    }

    @PutMapping
    public ChannelDto update(@Valid @RequestBody UpdateChannelCommand cmd) {
        return channelReadService.toDto(channelWriteService.update(cmd));
    }

    @PostMapping("/admin")
    public void create(@RequestBody CreateChannelAdminCommand cmd) {
        createChannelAdminUsecase.execute(cmd);
    }

    @DeleteMapping("/admin")
    public void delete(@RequestBody DeleteChannelAdminCommand cmd) {
        deleteChannelAdminUsecase.execute(cmd);
    }
}
