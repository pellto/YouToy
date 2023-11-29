package com.pellto.youtoy.domain.playlist.service;

import com.pellto.youtoy.domain.playlist.entity.Playlist;
import com.pellto.youtoy.domain.playlist.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlaylistReadService {
    private final PlaylistRepository playlistRepository;

    public Playlist getById(Long id) {
        return playlistRepository.findById(id).orElseThrow();
    }
}
