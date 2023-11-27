package com.pellto.youtoy.application.controller;

import com.pellto.youtoy.application.usecase.GetVideoCommentsUsecase;
import com.pellto.youtoy.domain.comment.dto.CreateCommentCommand;
import com.pellto.youtoy.domain.comment.entity.Comment;
import com.pellto.youtoy.domain.comment.service.CommentReadService;
import com.pellto.youtoy.domain.comment.service.CommentWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentWriteService commentWriteService;
    private final CommentReadService commentReadService;
    private final GetVideoCommentsUsecase getVideoCommentsUsecase;

    @PostMapping
    public Comment create(@RequestBody CreateCommentCommand cmd) {
        return commentWriteService.create(cmd);
    }

    @GetMapping("/{id}")
    public Comment get(@PathVariable Long id) {
        return commentReadService.get(id);
    }

    @GetMapping("/video/{videoId}")
    public List<Comment> getVideoComments(@PathVariable Long videoId) {
        return getVideoCommentsUsecase.execute(videoId, true);
    }

    @GetMapping("/short/{shortId}")
    public List<Comment> getShortComments(@PathVariable Long shortId) {
        return getVideoCommentsUsecase.execute(shortId, false);
    }
}
