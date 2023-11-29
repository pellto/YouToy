package com.pellto.youtoy.application.controller;

import com.pellto.youtoy.application.usecase.CancelLikeUsecase;
import com.pellto.youtoy.application.usecase.CreateLikeUsecase;
import com.pellto.youtoy.domain.like.dto.CreateLikeCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/like")
public class LikeController {
    private final CreateLikeUsecase createLikeUsecase;
    private final CancelLikeUsecase cancelLikeUsecase;

    @PostMapping
    public void like(@RequestBody CreateLikeCommand cmd) {
        createLikeUsecase.executeLike(cmd);
    }

    @DeleteMapping("/{id}")
    public void likeCancel(@PathVariable Long id) {
        cancelLikeUsecase.executeLike(id);
    }

    @PostMapping("/dislike")
    public void dislike(@RequestBody CreateLikeCommand cmd) {
        createLikeUsecase.executeDislike(cmd);
    }

    @DeleteMapping("/dislike/{id}")
    public void dislikeCancel(@PathVariable Long id) {
        cancelLikeUsecase.executeDislike(id);
    }
}
