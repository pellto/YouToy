package com.pellto.youtoy.domain.playlist.service;

import com.pellto.youtoy.domain.playlist.dto.PlaylistVideoDto;
import com.pellto.youtoy.domain.playlist.entity.PlaylistVideo;
import com.pellto.youtoy.domain.playlist.repository.PlaylistVideoRepository;
import com.pellto.youtoy.util.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PlaylistVideoReadService {
    private final PlaylistVideoRepository playlistVideoRepository;

    public PlaylistVideo get(Long id) {
        var playlistVideo = playlistVideoRepository.findById(id).orElse(null);
        Assert.notNull(playlistVideo, ErrorCode.NOT_EXIST_PLAYLIST_VIDEO.getMessage());
        return playlistVideo;
    }

    public List<PlaylistVideoDto> getByPlaylistId(Long playlistId) {
        return playlistVideoRepository.findByPlaylistId(playlistId).stream().map(this::toDto).toList();
    }

    public PlaylistVideoDto toDto(PlaylistVideo playlistVideo) {
        return new PlaylistVideoDto(
                playlistVideo.getId(),
                playlistVideo.getPlaylistId(),
                playlistVideo.getVideoId(),
                playlistVideo.getVideoType()
        );
    }
}
