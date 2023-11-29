package com.pellto.youtoy.domain.playlist.service;

import com.pellto.youtoy.domain.playlist.dto.CreatePlaylistVideoCommand;
import com.pellto.youtoy.domain.playlist.entity.PlaylistVideo;
import com.pellto.youtoy.domain.playlist.repository.PlaylistVideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@RequiredArgsConstructor
@Service
public class PlaylistVideoWriteService {
    private final PlaylistVideoRepository playlistVideoRepository;

    public PlaylistVideo create(CreatePlaylistVideoCommand cmd) {
        // TODO: playlist exist check at usecase
        // TODO: videoId exist check at usecase
        var playlistVideo = PlaylistVideo.builder()
                .playlistId(cmd.playlistId())
                .videoId(cmd.videoId())
                .videoType(cmd.videoType())
                .build();
        return playlistVideoRepository.save(playlistVideo);
    }

    public void delete(Long id) {
        PlaylistVideo playlistVideo = playlistVideoRepository.findById(id).orElse(null);
        Assert.notNull(playlistVideo, "존재하지 않습니다.");
        playlistVideoRepository.delete(playlistVideo.getId());
    }
}
