package com.pellto.youtoy.application.controller;

import com.pellto.youtoy.application.usecase.CreateLikeUsecase;
import com.pellto.youtoy.domain.like.dto.CreateLikeCommand;
import com.pellto.youtoy.domain.like.service.DislikeWriteService;
import com.pellto.youtoy.domain.like.service.LikeWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/like")
public class LikeController {
    private final LikeWriteService likeWriteService;
    private final CreateLikeUsecase createLikeUsecase;
    private final DislikeWriteService dislikeWriteService;

    @PostMapping
    public void like(@RequestBody CreateLikeCommand cmd) {
        createLikeUsecase.executeLike(cmd);
    }

    @DeleteMapping("/{id}")
    public void likeCancel(@PathVariable Long id) {
        likeWriteService.cancel(id);
    }

    @PostMapping("/dislike")
    public void dislike(@RequestBody CreateLikeCommand cmd) {
        createLikeUsecase.executeDislike(cmd);
    }

    @DeleteMapping("/dislike/{id}")
    public void dislikeCancel(@PathVariable Long id) {
        dislikeWriteService.cancel(id);
    }
}
