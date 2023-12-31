package com.pellto.youtoy.domain.view.service;

import com.pellto.youtoy.domain.view.dto.VideoDto;
import com.pellto.youtoy.domain.view.entity.Video;
import com.pellto.youtoy.domain.view.repository.VideoRepository;
import com.pellto.youtoy.util.types.VideoTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VideoReadService {
    private final VideoRepository videoRepository;

    public VideoDto getVideo(Long id) {
        return toDto(videoRepository.findById(id).orElseThrow());
    }

    private VideoDto toDto(Video video) {
        return new VideoDto(
                video.getId(),
                video.getChannelId(),
                video.getTitle(),
                video.getViewCount(),
                video.getDescription(),
                video.getCreatedAt(),
                video.getLikeCount(),
                VideoTypes.VIDEO_TYPE.isVideo()
        );
    }

    public boolean existVideo(Long id) {
        return videoRepository.existVideo(id);
    }
}
