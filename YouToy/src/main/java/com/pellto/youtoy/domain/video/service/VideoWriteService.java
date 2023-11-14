package com.pellto.youtoy.domain.video.service;

import com.pellto.youtoy.domain.video.dto.UpdateVideoCommand;
import com.pellto.youtoy.domain.video.dto.UploadVideoCommand;
import com.pellto.youtoy.domain.video.entity.Video;
import com.pellto.youtoy.domain.video.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VideoWriteService {
    private final VideoRepository videoRepository;

    public Video upload(UploadVideoCommand cmd) {
        var video = Video.builder()
                .channelId(cmd.channelId())
                .title(cmd.title())
                .description(cmd.description())
                .build();

        return videoRepository.save(video);
    }

    public Video update(UpdateVideoCommand cmd) {
        var video = videoRepository.findById(cmd.id()).orElseThrow();
        boolean changeChecker = false;
        if (cmd.title() != null) {
            video.setTitle(cmd.title());
            changeChecker = true;
        }
        if (cmd.description() != null) {
            video.setDescription(cmd.description());
            changeChecker = true;
        }
        if (changeChecker) return videoRepository.save(video);
        return video;
    }


    public void remove(Long id) {
        if (!videoRepository.existVideo(id)) {
            throw new UnsupportedOperationException("Video가 존재하지 않습니다.");
        }
        videoRepository.delete(id);
    }
}
