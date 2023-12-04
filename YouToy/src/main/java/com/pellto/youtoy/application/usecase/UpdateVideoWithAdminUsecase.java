package com.pellto.youtoy.application.usecase;

import com.pellto.youtoy.domain.view.dto.UpdateVideoCommand;
import com.pellto.youtoy.domain.view.entity.Video;
import com.pellto.youtoy.domain.view.service.VideoWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateVideoWithAdminUsecase {
    private final VideoWriteService videoWriteService;
    private final AdminAuthorizeUsecase adminAuthorizeUsecase;

    // TODO: entity to dto in usecase
    public Video execute(UpdateVideoCommand cmd) {
        if (adminAuthorizeUsecase.execute(cmd))
            return videoWriteService.update(cmd);
        return null;
    }
}
