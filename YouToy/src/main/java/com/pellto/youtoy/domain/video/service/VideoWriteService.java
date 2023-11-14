package com.pellto.youtoy.domain.video.service;

import com.pellto.youtoy.domain.video.dto.UpdateVideoCommand;
import com.pellto.youtoy.domain.video.dto.UploadVideoCommand;
import com.pellto.youtoy.domain.video.entity.Video;
import com.pellto.youtoy.domain.video.repository.VideoRepository;
import com.pellto.youtoy.util.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            throw new UnsupportedOperationException(ErrorCode.NOT_EXIST_VIDEO.getMessage());
        }
        videoRepository.delete(id);
    }

    @Transactional
    // TODO: Change Transactional
    public void incrementViewCount(Long id) {
        Video video = videoRepository.findById(id).orElseThrow();
        video.incrementViewCount();
        videoRepository.save(video);
    }
}
