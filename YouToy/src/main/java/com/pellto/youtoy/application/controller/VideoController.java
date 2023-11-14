package com.pellto.youtoy.application.controller;

import com.pellto.youtoy.domain.video.dto.VideoUploadCommand;
import com.pellto.youtoy.domain.video.entity.Video;
import com.pellto.youtoy.domain.video.service.VideoReadService;
import com.pellto.youtoy.domain.video.service.VideoWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/video")
public class VideoController {
    private final VideoWriteService videoWriteService;
    private final VideoReadService videoReadService;

    @PostMapping
    public Video upload(@RequestBody VideoUploadCommand cmd) {
        return videoWriteService.upload(cmd);
    }

    @GetMapping("/{id}")
    public Video get(@PathVariable Long id) {
        return videoReadService.getVideo(id);
    }
}
