package com.pellto.youtoy.domain.video.api;

import com.pellto.youtoy.domain.base.dto.WriteCommentRequest;
import com.pellto.youtoy.domain.video.application.VideoCommentReadService;
import com.pellto.youtoy.domain.video.application.VideoCommentWriteService;
import com.pellto.youtoy.domain.video.application.VideoReadService;
import com.pellto.youtoy.domain.video.application.VideoWriteService;
import com.pellto.youtoy.domain.video.dto.VideoCommentDto;
import com.pellto.youtoy.domain.video.dto.VideoDto;
import com.pellto.youtoy.domain.video.dto.VideoUploadRequest;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/videos")
@RequiredArgsConstructor
@RestController
public class VideoController {

  private final VideoReadService videoReadService;
  private final VideoWriteService videoWriteService;
  private final VideoCommentReadService commentReadService;
  private final VideoCommentWriteService commentWriteService;

  @PostMapping
  public VideoDto upload(@RequestBody @Valid VideoUploadRequest req) {
    return videoWriteService.write(req);
  }

  @GetMapping
  public List<VideoDto> findAllVideo() {
    return videoReadService.findAll();
  }

  @PostMapping("/comments")
  public VideoCommentDto writeComment(@RequestBody @Valid WriteCommentRequest req) {
    return commentWriteService.write(req);
  }

  @GetMapping("/comments")
  public List<VideoCommentDto> findAllComments() {
    return commentReadService.findAll();
  }
}
