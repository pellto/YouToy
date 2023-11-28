package com.pellto.youtoy.domain.comment.service;

import com.pellto.youtoy.domain.comment.entity.Comment;
import com.pellto.youtoy.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentReadService {
    private final CommentRepository commentRepository;

    public Comment get(Long id) {
        return commentRepository.findById(id).orElseThrow();
    }

    public List<Comment> getByVideoId(Long videoId, boolean isVideo) {
        return commentRepository.findByVideoId(videoId, isVideo);
    }
}
