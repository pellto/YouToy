package com.pellto.youtoy.domain.playlist.service;

import com.pellto.youtoy.domain.playlist.dto.CreatePlaylistCommand;
import com.pellto.youtoy.domain.playlist.dto.UpdatePlaylistCommand;
import com.pellto.youtoy.domain.playlist.entity.Playlist;
import com.pellto.youtoy.domain.playlist.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class PlaylistWriteService {
    private final PlaylistRepository playlistRepository;

    public Playlist create(CreatePlaylistCommand cmd) {
        if (cmd.channelId() == null) {
            throw new UnsupportedOperationException("필수입니다.");
        }
        var playlist = Playlist
                .builder()
                .channelId(cmd.channelId())
                .title(cmd.title())
                .targetRange(cmd.targetRange())
                .build();

        return playlistRepository.save(playlist);
    }

    public void update(UpdatePlaylistCommand cmd) {
        boolean changeChecker = false;
        if (cmd.id() == null) {
            throw new UnsupportedOperationException("필수입니다");
        }
        var playlist = playlistRepository.findById(cmd.id()).orElseThrow();
        
        if (cmd.title() != null && !cmd.title().equals(playlist.getTitle())) {
            playlist.setTitle(cmd.title());
            changeChecker = true;
        }
        if (cmd.targetRange() != null && !Objects.equals(cmd.targetRange(), playlist.getTargetRange())) {
            playlist.setTargetRange(cmd.targetRange());
            changeChecker = true;
        }
        if (changeChecker) playlistRepository.save(playlist);
    }
}
