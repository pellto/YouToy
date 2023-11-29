package com.pellto.youtoy.domain.like.service;

import com.pellto.youtoy.domain.like.dto.CreateLikeCommand;
import com.pellto.youtoy.domain.like.dto.LikeDto;
import com.pellto.youtoy.domain.like.entity.Like;
import com.pellto.youtoy.domain.like.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikeReadService {
    private final LikeRepository likeRepository;

    public LikeDto getById(Long id) {
        var like = likeRepository.findById(id).orElseThrow();
        return toDto(like);
    }

    public LikeDto getByCreateCmd(CreateLikeCommand cmd) {
        var like = likeRepository.findByCreateCmd(cmd).orElse(null);
        return like == null ? null : toDto(like);
    }

    private LikeDto toDto(Like like) {
        return new LikeDto(
                like.getId(),
                like.getVideoId(),
                like.getVideoType(),
                like.getCommentId(),
                like.getUserId()
        );
    }
}
