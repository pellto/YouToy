package com.pellto.youtoy.application.controller;

import com.pellto.youtoy.application.usecase.*;
import com.pellto.youtoy.domain.view.dto.*;
import com.pellto.youtoy.domain.view.entity.Shorts;
import com.pellto.youtoy.domain.view.entity.Video;
import com.pellto.youtoy.domain.view.service.ShortReadService;
import com.pellto.youtoy.domain.view.service.ShortWriteService;
import com.pellto.youtoy.domain.view.service.VideoReadService;
import com.pellto.youtoy.domain.view.service.VideoWriteService;
import com.pellto.youtoy.util.error.ErrorCode;
import com.pellto.youtoy.util.types.VideoTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;
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
    private final ViewVideoShortUsecase viewVideoShortUsecase;

    @PostMapping
    public Video upload(@RequestBody UploadVideoCommand cmd) {
        return uploadVideoWithAdminUsecase.execute(cmd);
    }

    @PutMapping
    public Video update(@RequestBody UpdateVideoCommand cmd) {
        return updateVideoWithAdminUsecase.execute(cmd);
    }

    @GetMapping("/{id}")
    public VideoDto get(@PathVariable Long id) {
        return videoReadService.getVideo(id);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        videoWriteService.remove(id);
    }

    @PutMapping("/view/increment")
    public void increase(@RequestBody CreateViewHistoryCommand cmd) {
        Assert.isTrue(
                cmd.videoType().equals(VideoTypes.VIDEO_TYPE.getValue()),
                ErrorCode.INCREASE_BAD_REQUEST.getMessage()
        );
        viewVideoShortUsecase.execute(cmd);
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
    public ShortsDto getShort(@PathVariable Long id) {
        return shortReadService.getShort(id);
    }

    @DeleteMapping("/short/{id}")
    public void removeShort(@PathVariable Long id) {
        shortWriteService.remove(id);
    }

    @PutMapping("/short/view/increment")
    public void increaseShort(@RequestBody CreateViewHistoryCommand cmd) {
        Assert.isTrue(
                cmd.videoType().equals(VideoTypes.SHORTS_TYPE.getValue()),
                ErrorCode.INCREASE_BAD_REQUEST.getMessage()
        );
        viewVideoShortUsecase.execute(cmd);
    }
}
