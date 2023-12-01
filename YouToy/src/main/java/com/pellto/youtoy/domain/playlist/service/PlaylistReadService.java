package com.pellto.youtoy.domain.playlist.service;

import com.pellto.youtoy.domain.playlist.dto.PlaylistDto;
import com.pellto.youtoy.domain.playlist.entity.Playlist;
import com.pellto.youtoy.domain.playlist.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlaylistReadService {
    private final PlaylistRepository playlistRepository;

    public PlaylistDto getById(Long id) {
        return toDto(playlistRepository.findById(id).orElseThrow());
    }

    public boolean existById(Long id) {
        return playlistRepository.existById(id);
    }

    public PlaylistDto toDto(Playlist playlist) {
        return new PlaylistDto(
                playlist.getId(), playlist.getChannelId(),
                playlist.getTitle(), playlist.getTargetRange()
        );
    }
}
