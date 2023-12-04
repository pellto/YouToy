package com.pellto.youtoy.domain.view.service;

import com.pellto.youtoy.domain.view.dto.ShortsDto;
import com.pellto.youtoy.domain.view.entity.Shorts;
import com.pellto.youtoy.domain.view.repository.ShortRepository;
import com.pellto.youtoy.util.types.VideoTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ShortReadService {
    private final ShortRepository shortRepository;

    public ShortsDto getShort(Long id) {
        return toDto(shortRepository.findById(id).orElseThrow());
    }

    public boolean existShort(Long id) {
        return shortRepository.existShort(id);
    }

    public ShortsDto toDto(Shorts shorts) {
        return new ShortsDto(
                shorts.getId(),
                shorts.getChannelId(),
                shorts.getTitle(),
                shorts.getViewCount(),
                shorts.getDescription(),
                shorts.getCreatedAt(),
                shorts.getLikeCount(),
                VideoTypes.SHORTS_TYPE.isVideo()
        );
    }
}
