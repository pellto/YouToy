package com.pellto.youtoy.application.controller;

import com.pellto.youtoy.domain.channel.dto.ChannelDto;
import com.pellto.youtoy.domain.channel.dto.CreateChannelCommand;
import com.pellto.youtoy.domain.channel.dto.UpdateChannelCommand;
import com.pellto.youtoy.domain.channel.entity.Channel;
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

    @PostMapping
    public Channel create(@RequestBody CreateChannelCommand cmd) {
        return channelWriteService.create(cmd);
    }

    @GetMapping("/{id}")
    public ChannelDto getChannel(@PathVariable Long id) {
        return channelReadService.getChannel(id);
    }

    @PutMapping
    public void update(@Valid @RequestBody UpdateChannelCommand cmd) {
        channelWriteService.update(cmd);
    }
}
