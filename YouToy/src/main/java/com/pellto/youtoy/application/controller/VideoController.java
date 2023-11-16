package com.pellto.youtoy.application.controller;

import com.pellto.youtoy.application.usecase.UpdateShortWithAdminUsecase;
import com.pellto.youtoy.application.usecase.UpdateVideoWithAdminUsecase;
import com.pellto.youtoy.application.usecase.UploadShortWithAdminUsecase;
import com.pellto.youtoy.application.usecase.UploadVideoWithAdminUsecase;
import com.pellto.youtoy.domain.video.dto.UpdateShortCommand;
import com.pellto.youtoy.domain.video.dto.UpdateVideoCommand;
import com.pellto.youtoy.domain.video.dto.UploadShortCommand;
import com.pellto.youtoy.domain.video.dto.UploadVideoCommand;
import com.pellto.youtoy.domain.video.entity.Shorts;
import com.pellto.youtoy.domain.video.entity.Video;
import com.pellto.youtoy.domain.video.service.ShortReadService;
import com.pellto.youtoy.domain.video.service.ShortWriteService;
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
    private final ShortWriteService shortWriteService;
    private final ShortReadService shortReadService;
    private final UploadVideoWithAdminUsecase uploadVideoWithAdminUsecase;
    private final UploadShortWithAdminUsecase uploadShortWithAdminUsecase;
    private final UpdateVideoWithAdminUsecase updateVideoWithAdminUsecase;
    private final UpdateShortWithAdminUsecase updateShortWithAdminUsecase;

    @PostMapping
    public Video upload(@RequestBody UploadVideoCommand cmd) {
        return uploadVideoWithAdminUsecase.execute(cmd);
    }

    @PutMapping
    public Video update(@RequestBody UpdateVideoCommand cmd) {
        return updateVideoWithAdminUsecase.execute(cmd);
    }

    @GetMapping("/{id}")
    public Video get(@PathVariable Long id) {
        return videoReadService.getVideo(id);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        videoWriteService.remove(id);
    }

    @PutMapping("/{id}/view/increment")
    public void increase(@PathVariable Long id) {
        videoWriteService.incrementViewCount(id);
    }

    @PostMapping("/short")
    public Shorts uploadShort(@RequestBody UploadShortCommand cmd) {
        return uploadShortWithAdminUsecase.execute(cmd);
    }

    @PutMapping("/short")
    public Shorts updateShort(@RequestBody UpdateShortCommand cmd) {
        return updateShortWithAdminUsecase.execute(cmd);
    }

    @GetMapping("/short/{id}")
    public Shorts getShort(@PathVariable Long id) {
        return shortReadService.getShort(id);
    }

    @DeleteMapping("/short/{id}")
    public void removeShort(@PathVariable Long id) {
        shortWriteService.remove(id);
    }

    @PutMapping("/short/{id}/view/increment")
    public void increaseShort(@PathVariable Long id) {
        shortWriteService.incrementViewCount(id);
    }
}
