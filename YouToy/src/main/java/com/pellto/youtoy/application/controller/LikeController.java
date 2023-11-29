package com.pellto.youtoy.application.controller;

import com.pellto.youtoy.domain.like.dto.CreateLikeCommand;
import com.pellto.youtoy.domain.like.service.DislikeWriteService;
import com.pellto.youtoy.domain.like.service.LikeWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/like")
public class LikeController {
    private final LikeWriteService likeWriteService;
    private final DislikeWriteService dislikeWriteService;

    @PostMapping
    public void like(@RequestBody CreateLikeCommand cmd) {
        likeWriteService.like(cmd);
    }

    @PostMapping("/dislike")
    public void dislike(@RequestBody CreateLikeCommand cmd) {
        dislikeWriteService.dislike(cmd);
    }
}
