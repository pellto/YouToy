package com.pellto.youtoy.application.controller;

import com.pellto.youtoy.application.usecase.CreateChannelPlaylistUsecase;
import com.pellto.youtoy.domain.playlist.dto.CreatePlaylistCommand;
import com.pellto.youtoy.domain.playlist.dto.PlaylistDto;
import com.pellto.youtoy.domain.playlist.dto.UpdatePlaylistCommand;
import com.pellto.youtoy.domain.playlist.entity.Playlist;
import com.pellto.youtoy.domain.playlist.service.PlaylistReadService;
import com.pellto.youtoy.domain.playlist.service.PlaylistWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/playlist")
public class PlaylistController {
    private final PlaylistWriteService playlistWriteService;
    private final CreateChannelPlaylistUsecase createChannelPlaylistUsecase;
    private final PlaylistReadService playlistReadService;

    @PostMapping
    public PlaylistDto create(@Valid @RequestBody CreatePlaylistCommand cmd) {
        return createChannelPlaylistUsecase.execute(cmd);
    }

    @PatchMapping
    public void update(@RequestBody UpdatePlaylistCommand cmd) {
        playlistWriteService.update(cmd);
    }

    @GetMapping("/{id}")
    public Playlist get(@PathVariable Long id) {
        return playlistReadService.getById(id);
    }
}
