package com.pellto.youtoy.domain.video.service;

import com.pellto.youtoy.domain.video.entity.Shorts;
import com.pellto.youtoy.domain.video.repository.ShortRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ShortReadService {
    private final ShortRepository shortRepository;

    public Shorts getShort(Long id) {
        return shortRepository.findById(id).orElseThrow();
    }
}
