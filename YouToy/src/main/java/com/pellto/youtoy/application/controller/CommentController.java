package com.pellto.youtoy.application.controller;

import com.pellto.youtoy.domain.comment.dto.CommentDto;
import com.pellto.youtoy.application.usecase.GetCommentsUsecase;
import com.pellto.youtoy.domain.comment.dto.CreateCommentCommand;
import com.pellto.youtoy.domain.comment.entity.Comment;
import com.pellto.youtoy.domain.comment.service.CommentReadService;
import com.pellto.youtoy.domain.comment.service.CommentWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentWriteService commentWriteService;
    private final CommentReadService commentReadService;
    private final GetCommentsUsecase getCommentsUsecase;

    @PostMapping
    public Comment create(@RequestBody CreateCommentCommand cmd) {
        return commentWriteService.create(cmd);
    }

    @GetMapping("/{id}")
    public Comment get(@PathVariable Long id) {
        return commentReadService.get(id);
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
