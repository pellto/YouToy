package com.pellto.youtoy.domain.comment.service;

import com.pellto.youtoy.domain.comment.dto.CreateCommentCommand;
import com.pellto.youtoy.domain.comment.entity.Comment;
import com.pellto.youtoy.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentWriteService {
    private final CommentRepository commentRepository;

    public Comment create(CreateCommentCommand cmd) {
        System.out.println("cmd = " + cmd);
        var comment = Comment.builder()
                .videoId(cmd.videoId())
                .userId(cmd.userId())
                .video(cmd.video())
                .content(cmd.content())
                .repliedCommentId(cmd.repliedCommentId())
                .build();
        return commentRepository.save(comment);
    }
}
