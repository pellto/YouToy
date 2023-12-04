package com.pellto.youtoy.application.usecase;

import com.pellto.youtoy.domain.playlist.dto.PlaylistVideoDto;
import com.pellto.youtoy.domain.playlist.service.PlaylistVideoReadService;
import com.pellto.youtoy.domain.view.dto.VideoContentsDto;
import com.pellto.youtoy.domain.view.service.ShortReadService;
import com.pellto.youtoy.domain.view.service.VideoReadService;
import com.pellto.youtoy.util.types.VideoTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetVideosInPlaylistUsecase {
    private final PlaylistVideoReadService playlistVideoReadService;
    private final VideoReadService videoReadService;
    private final ShortReadService shortReadService;

    public List<VideoContentsDto> execute(Long playListId) {
        var playlistVideoDtos = playlistVideoReadService.getByPlaylistId(playListId);
        return playlistVideoDtos.stream().map(this::getContent).toList();
    }

    private VideoContentsDto getContent(PlaylistVideoDto playlistVideoDto) {
        if (playlistVideoDto.videoType().equals(VideoTypes.VIDEO_TYPE.getValue())) {
            return videoReadService.getVideo(playlistVideoDto.videoId());
        }
        return shortReadService.getShort(playlistVideoDto.videoId());
    }
}
