package com.pellto.youtoy.domain.video.service;

import com.pellto.youtoy.domain.video.dto.VideoUploadCommand;
import com.pellto.youtoy.domain.video.entity.Video;
import com.pellto.youtoy.domain.video.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VideoReadService {
    private final VideoRepository videoRepository;

    public Video getVideo(Long id) {
        return videoRepository.findById(id).orElseThrow();
    }
}
