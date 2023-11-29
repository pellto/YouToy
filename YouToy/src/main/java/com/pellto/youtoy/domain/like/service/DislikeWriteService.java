package com.pellto.youtoy.domain.like.service;

import com.pellto.youtoy.domain.like.dto.CreateLikeCommand;
import com.pellto.youtoy.domain.like.entity.Dislike;
import com.pellto.youtoy.domain.like.entity.Like;
import com.pellto.youtoy.domain.like.repository.DislikeRepository;
import com.pellto.youtoy.domain.like.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class DislikeWriteService {
    private final DislikeRepository dislikeRepository;

    public Dislike dislike(CreateLikeCommand cmd) {
        if (cmd.videoType() == null && cmd.commentId() == null) {
            throw new UnsupportedOperationException("지원하지않는 dislike 유형입니다.");
        }

        var dislike = Dislike.builder()
                .videoId(cmd.videoId())
                .videoType(cmd.videoType())
                .commentId(cmd.commentId())
                .userId(cmd.userId())
                .build();

        return dislikeRepository.save(dislike);
    }

    public void cancel(Long id) {
        if (!dislikeRepository.existById(id)) {
            throw new UnsupportedOperationException("존재하지 않습니다.");
        }
        dislikeRepository.deleteById(id);
    }
}
