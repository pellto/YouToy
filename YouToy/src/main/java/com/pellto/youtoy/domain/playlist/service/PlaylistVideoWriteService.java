package com.pellto.youtoy.domain.playlist.service;

import com.pellto.youtoy.domain.playlist.dto.CreatePlaylistVideoCommand;
import com.pellto.youtoy.domain.playlist.dto.PlaylistVideoDto;
import com.pellto.youtoy.domain.playlist.entity.PlaylistVideo;
import com.pellto.youtoy.domain.playlist.repository.PlaylistVideoRepository;
import com.pellto.youtoy.util.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@RequiredArgsConstructor
@Service
public class PlaylistVideoWriteService {
    private final PlaylistVideoRepository playlistVideoRepository;
    private final PlaylistVideoReadService playlistVideoReadService;

    public PlaylistVideoDto create(CreatePlaylistVideoCommand cmd) {
        var playlistVideo = PlaylistVideo.builder()
                .playlistId(cmd.playlistId())
                .videoId(cmd.videoId())
                .videoType(cmd.videoType())
                .build();
        return playlistVideoReadService.toDto(playlistVideoRepository.save(playlistVideo));
    }

    public void delete(Long id) {
        PlaylistVideo playlistVideo = playlistVideoRepository.findById(id).orElse(null);
        Assert.notNull(playlistVideo, ErrorCode.NOT_EXIST_PLAYLIST_VIDEO.getMessage());
        playlistVideoRepository.delete(playlistVideo.getId());
    }
}
