package com.pellto.youtoy.domain.like.service;

import com.pellto.youtoy.domain.like.dto.CreateLikeCommand;
import com.pellto.youtoy.domain.like.dto.LikeDto;
import com.pellto.youtoy.domain.like.entity.Like;
import com.pellto.youtoy.domain.like.repository.LikeRepository;
import com.pellto.youtoy.util.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class LikeWriteService {
    private final LikeRepository likeRepository;

    public Like like(CreateLikeCommand cmd) {
        var like = Like.builder()
                .videoId(cmd.videoId())
                .videoType(cmd.videoType())
                .commentId(cmd.commentId())
                .userId(cmd.userId())
                .build();

        return likeRepository.save(like);
    }

    public void cancel(Long id) {
        if (!likeRepository.existById(id)) {
            throw new UnsupportedOperationException(ErrorCode.NOT_EXIST_LIKE.getMessage());
        }
        likeRepository.deleteById(id);
    }

    public void cancel(LikeDto like) {
        likeRepository.deleteById(like.id());
    }
}
