package com.pellto.youtoy.application.controller;

import com.pellto.youtoy.domain.comment.dto.CreateCommentCommand;
import com.pellto.youtoy.domain.comment.entity.Comment;
import com.pellto.youtoy.domain.comment.service.CommentReadService;
import com.pellto.youtoy.domain.comment.service.CommentWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentWriteService commentWriteService;
    private final CommentReadService commentReadService;

    @PostMapping
    public Comment create(@RequestBody CreateCommentCommand cmd) {
        return commentWriteService.create(cmd);
    }

    @GetMapping("/{id}")
    public Comment get(@PathVariable Long id) {
        return commentReadService.get(id);
    }
}
