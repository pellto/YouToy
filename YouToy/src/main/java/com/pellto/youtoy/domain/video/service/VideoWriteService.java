package com.pellto.youtoy.domain.video.service;

import com.pellto.youtoy.domain.video.dto.VideoUploadCommand;
import com.pellto.youtoy.domain.video.entity.Video;
import com.pellto.youtoy.domain.video.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VideoWriteService {
    private final VideoRepository videoRepository;

    public Video upload(VideoUploadCommand cmd) {
        var video = Video.builder()
                .channelId(cmd.channelId())
                .title(cmd.title())
                .description(cmd.description())
                .build();

        return videoRepository.save(video);
    }
}
