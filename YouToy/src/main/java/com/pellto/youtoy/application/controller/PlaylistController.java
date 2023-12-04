package com.pellto.youtoy.application.controller;

import com.pellto.youtoy.application.usecase.CreateChannelPlaylistUsecase;
import com.pellto.youtoy.application.usecase.CreatePlaylistVideoUsecase;
import com.pellto.youtoy.application.usecase.GetVideosInPlaylistUsecase;
import com.pellto.youtoy.domain.playlist.dto.*;
import com.pellto.youtoy.domain.playlist.service.PlaylistReadService;
import com.pellto.youtoy.domain.playlist.service.PlaylistVideoWriteService;
import com.pellto.youtoy.domain.playlist.service.PlaylistWriteService;
import com.pellto.youtoy.domain.view.dto.VideoContentsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/playlist")
public class PlaylistController {
    private final PlaylistWriteService playlistWriteService;
    private final PlaylistVideoWriteService playlistVideoWriteService;
    private final CreateChannelPlaylistUsecase createChannelPlaylistUsecase;
    private final CreatePlaylistVideoUsecase createPlaylistVideoUsecase;
    private final GetVideosInPlaylistUsecase getVideosInPlaylistUsecase;
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
    public PlaylistDto get(@PathVariable Long id) {
        return playlistReadService.getById(id);
    }

    @PostMapping("/video")
    public PlaylistVideoDto create(@Valid @RequestBody CreatePlaylistVideoCommand cmd) {
        return createPlaylistVideoUsecase.execute(cmd);
    }

    @DeleteMapping("/video/{id}")
    public void delete(@Valid @PathVariable Long id) {
        playlistVideoWriteService.delete(id);
    }

    @GetMapping("/video/{playlistId}/contents")
    public List<VideoContentsDto> getByPlaylistId(@PathVariable Long playlistId) {
        return getVideosInPlaylistUsecase.execute(playlistId);
    }
}
