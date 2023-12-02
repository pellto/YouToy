package com.pellto.youtoy.domain.comment.service;

import com.pellto.youtoy.domain.comment.dto.CommentDto;
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
    private final CommentReadService commentReadService;

    public CommentDto create(CreateCommentCommand cmd) {
        var comment = Comment.builder()
                .videoId(cmd.videoId())
                .userId(cmd.userId())
                .video(cmd.video())
                .content(cmd.content())
                .repliedCommentId(cmd.repliedCommentId())
                .build();
        return commentReadService.toDto(commentRepository.save(comment));
    }

    public CommentDto update(UpdateCommentCommand cmd) {
        var comment = commentRepository.findById(cmd.id()).orElseThrow();
        if (!Objects.equals(cmd.content(), comment.getContent())) {
            comment.setContent(cmd.content());
            commentRepository.save(comment);
        }
        return commentReadService.toDto(comment);
    }

    public void increaseLikeCount(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow();
        comment.increaseLikeCount();
        commentRepository.save(comment);
    }

    public void decreaseLikeCount(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow();
        comment.decreaseLikeCount();
        commentRepository.save(comment);
    }
}
