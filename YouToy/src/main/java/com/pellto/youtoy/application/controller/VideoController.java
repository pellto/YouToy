package com.pellto.youtoy.application.controller;

import com.pellto.youtoy.application.usecase.UpdateShortWithAdminUsecase;
import com.pellto.youtoy.application.usecase.UpdateVideoWithAdminUsecase;
import com.pellto.youtoy.application.usecase.UploadShortWithAdminUsecase;
import com.pellto.youtoy.application.usecase.UploadVideoWithAdminUsecase;
import com.pellto.youtoy.application.usecase.ViewVideoShortUsecase;
import com.pellto.youtoy.domain.view.dto.CreateViewHistoryCommand;
import com.pellto.youtoy.domain.view.dto.ShortsDto;
import com.pellto.youtoy.domain.view.dto.UpdateShortCommand;
import com.pellto.youtoy.domain.view.dto.UpdateVideoCommand;
import com.pellto.youtoy.domain.view.dto.UploadShortCommand;
import com.pellto.youtoy.domain.view.dto.UploadVideoCommand;
import com.pellto.youtoy.domain.view.dto.VideoDto;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

  @GetMapping("/{id}")
  public VideoDto get(@PathVariable Long id) {
    return videoReadService.getVideo(id);
  }

  @GetMapping("/short/{id}")
  public ShortsDto getShort(@PathVariable Long id) {
    return shortReadService.getShort(id);
  }

  @PutMapping("/view/increment")
  public void increase(@RequestBody CreateViewHistoryCommand cmd) {
    Assert.isTrue(
        cmd.videoType().equals(VideoTypes.VIDEO_TYPE.getValue()),
        ErrorCode.INCREASE_BAD_REQUEST.getMessage()
    );
    viewVideoShortUsecase.execute(cmd);
  }

  @PutMapping("/short/view/increment")
  public void increaseShort(@RequestBody CreateViewHistoryCommand cmd) {
    Assert.isTrue(
        cmd.videoType().equals(VideoTypes.SHORTS_TYPE.getValue()),
        ErrorCode.INCREASE_BAD_REQUEST.getMessage()
    );
    viewVideoShortUsecase.execute(cmd);
  }

  @DeleteMapping("/{id}")
  public void remove(@PathVariable Long id) {
    videoWriteService.remove(id);
  }

  @DeleteMapping("/short/{id}")
  public void removeShort(@PathVariable Long id) {
    shortWriteService.remove(id);
  }

  @PutMapping
  public Video update(@RequestBody UpdateVideoCommand cmd) {
    return updateVideoWithAdminUsecase.execute(cmd);
  }

  @PutMapping("/short")
  public Shorts updateShort(@RequestBody UpdateShortCommand cmd) {
    return updateShortWithAdminUsecase.execute(cmd);
  }

  @PostMapping
  public Video upload(@RequestBody UploadVideoCommand cmd) {
    return uploadVideoWithAdminUsecase.execute(cmd);
  }

  @PostMapping("/short")
  public Shorts uploadShort(@RequestBody UploadShortCommand cmd) {
    return uploadShortWithAdminUsecase.execute(cmd);
  }
}
