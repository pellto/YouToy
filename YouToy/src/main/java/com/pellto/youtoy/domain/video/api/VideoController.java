package com.pellto.youtoy.domain.video.api;

import com.pellto.youtoy.domain.base.dto.WriteCommentRequest;
import com.pellto.youtoy.domain.base.dto.WriteInterestRequest;
import com.pellto.youtoy.domain.video.application.VideoCommentReadService;
import com.pellto.youtoy.domain.video.application.VideoCommentWriteService;
import com.pellto.youtoy.domain.video.application.VideoInterestService;
import com.pellto.youtoy.domain.video.application.VideoReadService;
import com.pellto.youtoy.domain.video.application.VideoReplyCommentService;
import com.pellto.youtoy.domain.video.application.VideoWriteService;
import com.pellto.youtoy.domain.video.dto.VideoCommentDto;
import com.pellto.youtoy.domain.video.dto.VideoDto;
import com.pellto.youtoy.domain.video.dto.VideoInterestDto;
import com.pellto.youtoy.domain.video.dto.VideoReplyCommentDto;
import com.pellto.youtoy.domain.video.dto.VideoUploadRequest;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  private final VideoReplyCommentService videoReplyCommentService;
  private final VideoInterestService videoInterestService;

  @PostMapping
  public VideoDto upload(@RequestBody @Valid VideoUploadRequest req) {
    return videoWriteService.write(req);
  }

  @GetMapping
  public List<VideoDto> findAllVideo() {
    return videoReadService.findAll();
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    videoWriteService.deleteById(id);
  }

  @PostMapping("/comments")
  public VideoCommentDto writeComment(@RequestBody @Valid WriteCommentRequest req) {
    return commentWriteService.write(req);
  }

  @GetMapping("/comments")
  public List<VideoCommentDto> findAllComments() {
    return commentReadService.findAll();
  }

  @PostMapping("/comments/replies")
  public VideoReplyCommentDto writeReply(@RequestBody @Valid WriteCommentRequest req) {
    return videoReplyCommentService.write(req);
  }

  @GetMapping("/comments/{parentId}/replies")
  public List<VideoReplyCommentDto> findAllRepliesByParentId(@PathVariable Long parentId) {
    return videoReplyCommentService.findAllByParentId(parentId);
  }

  @DeleteMapping("/comments/replies/{id}")
  public void deleteReplyById(@PathVariable Long id) {
    videoReplyCommentService.deleteById(id);
  }

  @PostMapping("/interests")
  public VideoInterestDto interestVideo(@RequestBody @Valid WriteInterestRequest req) {
    return videoInterestService.write(req);
  }

  @GetMapping("/{contentsId}/interests/")
  public List<VideoInterestDto> findAllInterestByContentsId(@PathVariable Long contentsId) {
    return videoInterestService.findAllByContentsId(contentsId);
  }

  @DeleteMapping("/interests/{id}")
  public void deleteInterestById(@PathVariable Long id) {
    videoInterestService.deleteById(id);
  }


}
