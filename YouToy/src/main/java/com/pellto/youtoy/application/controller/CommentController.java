package com.pellto.youtoy.application.controller;

import com.pellto.youtoy.application.usecase.CreateCommentUsecase;
import com.pellto.youtoy.application.usecase.UpdateCommentUsecase;
import com.pellto.youtoy.domain.comment.dto.CommentDto;
import com.pellto.youtoy.application.usecase.GetCommentsUsecase;
import com.pellto.youtoy.domain.comment.dto.CreateCommentCommand;
import com.pellto.youtoy.domain.comment.dto.UpdateCommentCommand;
import com.pellto.youtoy.domain.comment.service.CommentReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CreateCommentUsecase createCommentUsecase;
    private final UpdateCommentUsecase updateCommentUsecase;
    private final GetCommentsUsecase getCommentsUsecase;
    private final CommentReadService commentReadService;


    @PostMapping
    public CommentDto create(@RequestBody CreateCommentCommand cmd) {
        return createCommentUsecase.execute(cmd);
    }

    @GetMapping("/{id}")
    public CommentDto get(@PathVariable Long id) {
        return commentReadService.get(id);
    }

    @PatchMapping
    public CommentDto update(@RequestBody UpdateCommentCommand cmd) {
        return updateCommentUsecase.execute(cmd);
    }

    @GetMapping("/replies/{id}")
    public List<CommentDto> getReplies(@PathVariable Long id) {
        return commentReadService.getReplies(id);
    }

    @GetMapping("/video/{videoId}")
    public List<CommentDto> getVideoComments(@PathVariable Long videoId) {
        return getCommentsUsecase.execute(videoId, true);
    }

    @GetMapping("/short/{shortId}")
    public List<CommentDto> getShortComments(@PathVariable Long shortId) {
        return getCommentsUsecase.execute(shortId, false);
    }
}
