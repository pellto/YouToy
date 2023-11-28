package com.pellto.youtoy.domain.comment.service;

import com.pellto.youtoy.domain.comment.dto.CreateCommentCommand;
import com.pellto.youtoy.domain.comment.dto.UpdateCommentCommand;
import com.pellto.youtoy.domain.comment.entity.Comment;
import com.pellto.youtoy.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CommentWriteService {
    private final CommentRepository commentRepository;

    public Comment create(CreateCommentCommand cmd) {
        var comment = Comment.builder()
                .videoId(cmd.videoId())
                .userId(cmd.userId())
                .video(cmd.video())
                .content(cmd.content())
                .repliedCommentId(cmd.repliedCommentId())
                .build();
        return commentRepository.save(comment);
    }

    public Comment update(UpdateCommentCommand cmd) {
        var comment = commentRepository.findById(cmd.id()).orElseThrow();
        if (!Objects.equals(cmd.content(), comment.getContent())) {
            comment.setContent(cmd.content());
            commentRepository.save(comment);
        }
        return comment;
    }
}
