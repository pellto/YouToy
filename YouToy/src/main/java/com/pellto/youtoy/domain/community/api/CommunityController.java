package com.pellto.youtoy.domain.community.api;

import com.pellto.youtoy.domain.community.application.CommunityCommentReadService;
import com.pellto.youtoy.domain.community.application.CommunityCommentWriteService;
import com.pellto.youtoy.domain.community.dto.CommunityCommentDto;
import com.pellto.youtoy.domain.community.dto.ModifyCommentRequest;
import com.pellto.youtoy.domain.community.dto.WriteCommentRequest;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/communities")
@RequiredArgsConstructor
@RestController
public class CommunityController {

  private final CommunityCommentReadService commentReadService;
  private final CommunityCommentWriteService commentWriteService;

  @GetMapping("/comments")
  public List<CommunityCommentDto> findAll() {
    return commentReadService.findAll();
  }

  @GetMapping("/comments/{id}")
  public CommunityCommentDto findById(@PathVariable Long id) {
    return commentReadService.findById(id);
  }

  @PostMapping("/comments")
  public CommunityCommentDto comment(@RequestBody @Valid WriteCommentRequest req) {
    return commentWriteService.write(req);
  }

  @PatchMapping("/comments")
  public CommunityCommentDto modifyComment(@RequestBody @Valid ModifyCommentRequest req) {
    return commentWriteService.modify(req);
  }
}
