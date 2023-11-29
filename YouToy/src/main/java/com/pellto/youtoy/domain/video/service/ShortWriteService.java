package com.pellto.youtoy.domain.video.service;

import com.pellto.youtoy.domain.video.dto.UpdateShortCommand;
import com.pellto.youtoy.domain.video.dto.UploadShortCommand;
import com.pellto.youtoy.domain.video.entity.Shorts;
import com.pellto.youtoy.domain.video.repository.ShortRepository;
import com.pellto.youtoy.util.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ShortWriteService {
    private final ShortRepository shortRepository;

    public Shorts upload(UploadShortCommand cmd) {
        var shorts = Shorts.builder()
                .channelId(cmd.channelId())
                .title(cmd.title())
                .description(cmd.description())
                .build();

        return shortRepository.save(shorts);
    }

    public Shorts update(UpdateShortCommand cmd) {
        var shorts = shortRepository.findById(cmd.id()).orElseThrow();
        boolean changeChecker = false;
        if (cmd.title() != null) {
            shorts.setTitle(cmd.title());
            changeChecker = true;
        }
        if (cmd.description() != null) {
            shorts.setDescription(cmd.description());
            changeChecker = true;
        }
        if (changeChecker) return shortRepository.save(shorts);
        return shorts;
    }


    public void remove(Long id) {
        if (!shortRepository.existShort(id)) {
            throw new UnsupportedOperationException(ErrorCode.NOT_EXIST_SHORT.getMessage());
        }
        shortRepository.delete(id);
    }

    @Transactional
    // TODO: Change Transactional
    public void increaseViewCount(Long id) {
        Shorts shorts = shortRepository.findById(id).orElseThrow();
        shorts.increaseViewCount();
        shortRepository.save(shorts);
    }

    public void increaseLikeCount(Long id) {
        Shorts shorts = shortRepository.findById(id).orElseThrow();
        shorts.increaseLikeCount();
        shortRepository.save(shorts);
    }

    public void decreaseLikeCount(Long id) {
        Shorts shorts = shortRepository.findById(id).orElseThrow();
        shorts.decreaseLikeCount();
        shortRepository.save(shorts);
    }
}
