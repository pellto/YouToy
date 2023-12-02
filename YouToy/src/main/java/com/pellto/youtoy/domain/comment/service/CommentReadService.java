package com.pellto.youtoy.domain.comment.service;

import com.pellto.youtoy.domain.comment.dto.CommentDto;
import com.pellto.youtoy.domain.comment.entity.Comment;
import com.pellto.youtoy.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentReadService {
    private final CommentRepository commentRepository;

    public CommentDto get(Long id) {
        return toDto(commentRepository.findById(id).orElseThrow());
    }

    public List<CommentDto> getByVideoIdAndVideo(Long videoId, boolean isVideo) {
        var comments = commentRepository.findByVideoIdAndVideo(videoId, isVideo);
        return comments.stream().map(this::toDto).toList();
    }

    public CommentDto toDto(Comment comment) {
        Long replyCnt = null;
        if (comment.getRepliedCommentId() == null)
            replyCnt = getReplyCommentCount(comment.getId());

        return new CommentDto(
                comment.getId(),
                comment.getUserId(),
                comment.getContent(),
                replyCnt,
                comment.getCreatedAt()
        );
    }

    public Long getReplyCommentCount(Long repliedCommentId) {
        return commentRepository.countByRepliedCommentId(repliedCommentId);
    }

    public List<CommentDto> getReplies(Long id) {
        var replies = commentRepository.findByRepliedCommentId(id);
        return replies.stream().map(this::toDto).toList();
    }

    public boolean existComment(Long id) {
        return commentRepository.existById(id);
    }
}
